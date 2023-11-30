package com.Guesmi.gestion.du.stock.services;


import com.Guesmi.gestion.du.stock.dto.ArticleDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeClientDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeFournisseurDto;
import com.Guesmi.gestion.du.stock.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {

  ArticleDto save(ArticleDto dto);

  ArticleDto findById(Integer id);

  ArticleDto findByCodeArticle(String codeArticle);

  List<ArticleDto> findAll();


  void delete(Integer id);

}
