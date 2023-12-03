package com.Guesmi.gestiondestock.dto.auth;




import com.Guesmi.gestiondestock.model.Entreprise;
import com.Guesmi.gestiondestock.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String email;
    private String idEntreprise;
    private String moteDePasse;

    private String photo;




    public String getEmail() {
        return this.email;
    }



}
