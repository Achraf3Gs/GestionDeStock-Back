package com.Guesmi.gestiondestock.repository;

import com.Guesmi.gestiondestock.model.CommandeClient;
import com.Guesmi.gestiondestock.model.CommandeFournisseur;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<CommandeClient> findAllByFournisseurId(Integer id);
}
