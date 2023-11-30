package com.Guesmi.gestion.du.stock.services;


import com.Guesmi.gestion.du.stock.dto.CommandeFournisseurDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeFournisseurDto;
import com.Guesmi.gestion.du.stock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDto save(CommandeFournisseurDto dto);

  CommandeFournisseurDto findById(Integer id);

  CommandeFournisseurDto findByCode(String code);

  List<CommandeFournisseurDto> findAll();


  void delete(Integer id);

}
