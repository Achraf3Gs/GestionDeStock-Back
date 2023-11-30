package com.Guesmi.gestion.du.stock.services;


import com.Guesmi.gestion.du.stock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  void delete(Integer id);




}
