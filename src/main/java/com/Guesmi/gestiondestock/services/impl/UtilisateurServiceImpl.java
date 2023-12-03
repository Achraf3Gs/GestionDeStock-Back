package com.Guesmi.gestiondestock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.Guesmi.gestiondestock.dto.UtilisateurDto;
import com.Guesmi.gestiondestock.exception.EntityNotFoundException;
import com.Guesmi.gestiondestock.exception.ErrorCodes;
import com.Guesmi.gestiondestock.exception.InvalidEntityException;
import com.Guesmi.gestiondestock.repository.UtilisateurRepository;
import com.Guesmi.gestiondestock.services.UtilisateurService;
import com.Guesmi.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  private UtilisateurRepository utilisateurRepository;

  @Autowired
  public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
    this.utilisateurRepository = utilisateurRepository;
  }

  @Override
  public UtilisateurDto save(UtilisateurDto dto) {
    List<String> errors = UtilisateurValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Utilisateur is not valid {}", dto);
      throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
    }

    return UtilisateurDto.fromEntity(
        utilisateurRepository.save(
            UtilisateurDto.toEntity(dto)
        )
    );
  }

  @Override
  public UtilisateurDto findById(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return null;
    }
    return utilisateurRepository.findById(id)
        .map(UtilisateurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
  }

  @Override
  public List<UtilisateurDto> findAll() {
    return utilisateurRepository.findAll().stream()
        .map(UtilisateurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return;
    }
    utilisateurRepository.deleteById(id);
  }


  @Override
  public UtilisateurDto findByEmail(String email) {
    return utilisateurRepository.findByEmail(email)
            .map(UtilisateurDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
                    ErrorCodes.UTILISATEUR_NOT_FOUND)
            );

    
  }



  }

