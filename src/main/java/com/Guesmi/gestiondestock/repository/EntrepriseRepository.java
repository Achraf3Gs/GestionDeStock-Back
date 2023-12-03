package com.Guesmi.gestiondestock.repository;


import com.Guesmi.gestiondestock.model.Article;
import com.Guesmi.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findEntrepriseById(String IdEntreprise);
}
