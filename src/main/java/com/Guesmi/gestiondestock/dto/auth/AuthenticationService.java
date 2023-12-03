package com.Guesmi.gestiondestock.dto.auth;









import com.Guesmi.gestiondestock.config.JwtService;
import com.Guesmi.gestiondestock.model.Entreprise;
import com.Guesmi.gestiondestock.model.Roles;
import com.Guesmi.gestiondestock.model.Utilisateur;
import com.Guesmi.gestiondestock.repository.EntrepriseRepository;
import com.Guesmi.gestiondestock.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UtilisateurRepository repository;

    private final EntrepriseRepository entrepriseRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getEmail();

        // Check if the email already exists in the database
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
        Entreprise entreprise = entrepriseRepository.findEntrepriseById(request.getIdEntreprise())
                .orElseThrow(() -> new NoSuchElementException("Entreprise not found"));

        var user= Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .entreprise(entreprise)
                .moteDePasse(passwordEncoder.encode(request.getMoteDePasse()))
                .photo(request.getPhoto())
                .build();
        repository.save(user);
        var jwtToken= jwtService.generateToken((Utilisateur) user);



        String name= user.getNom();
        String idEntreprise= String.valueOf(user.getEntreprise().getId());
        Integer id = user.getId();
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .idEntreprise(idEntreprise)
                .id(id)
                .name(name)
                .build();

    }




    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (user == null) {
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .message("invalid email/password")
                    .build();
            return response;
        } else {
            var jwtToken = jwtService.generateToken(user);
            String username = jwtService.extractUsername(jwtToken);
            String idEntreprise= String.valueOf(user.getEntreprise().getId());
            String nom= user.getNom();
            Integer id = user.getId();
            AuthenticationResponse response = AuthenticationResponse.builder()

                    .token(jwtToken)
                    .idEntreprise(idEntreprise)
                    .id(id)
                    .name(nom)
                    .build();
            return response;
        }
    }
}

