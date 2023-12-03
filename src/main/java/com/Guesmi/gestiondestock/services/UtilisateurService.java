package com.Guesmi.gestiondestock.services;


import com.Guesmi.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  UtilisateurDto findByEmail(String email);

  void delete(Integer id);




}