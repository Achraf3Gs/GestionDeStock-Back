package com.Guesmi.gestion.du.stock.services;


import com.Guesmi.gestion.du.stock.dto.CommandeClientDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeClientDto;
import com.Guesmi.gestion.du.stock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

  CommandeClientDto save(CommandeClientDto dto);


  CommandeClientDto findById(Integer id);

  CommandeClientDto findByCode(String code);

  List<CommandeClientDto> findAll();


  void delete(Integer id);

}
