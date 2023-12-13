package com.Guesmi.gestiondestock.config;



import com.Guesmi.gestiondestock.model.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String secret="achrafou";
    private static final String client="123456789";

    private static final String SECRET_KEY="8ba5bcf689a7b166bdbf984fb4668ecce6070d01db7e7078d2994ffffac48034";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractIdEntreprise(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get("idEntreprise");


    }

    private Utilisateur utilisateur;



    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    public String generateToken(Utilisateur utilisateur) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",utilisateur.getId()+"");
        claims.put("name", utilisateur.getNom());
        claims.put("idEntreprise", String.valueOf(utilisateur.getEntreprise().getId()));
        claims.put("address", utilisateur.getPrenom());
        
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(String.format("%s",utilisateur.getEmail()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*120))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();

    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }
    public boolean isTokenAdminValid(String token, UserDetails userDetails) {
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims  extractAllClaims(String token) {
        try {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception ex) {
            // Handle parsing exceptions
            ex.printStackTrace(); // Log the exception for debugging
            // You can throw a custom exception or return null indicating failure to parse
            // Throw an exception or return a default Claims object depending on your logic
            return null;
        }
    }


    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    boolean isValidSecretAndClientKeys(String secretKey, String clientKey) {
        // Implement your logic to validate the secret_key and client_key
        // Return true if they are valid, false if they are not
        return (secretKey != null && secretKey.equals(secret))
                && (clientKey != null && clientKey.equals(client));
    }



}