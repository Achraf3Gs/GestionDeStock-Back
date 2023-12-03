package com.Guesmi.gestiondestock.config;

import com.Guesmi.gestiondestock.model.Entreprise;
import com.Guesmi.gestiondestock.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails  {

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Instant dateDeNaissance;
    private String moteDePasse;
    private String photo;
    private Entreprise entreprise;
    private List<Roles> roles;

    public UserDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return email;
    }
}
















