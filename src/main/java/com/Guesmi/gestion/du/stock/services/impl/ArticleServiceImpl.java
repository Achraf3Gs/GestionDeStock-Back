package com.Guesmi.gestion.du.stock.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import com.Guesmi.gestion.du.stock.dto.ArticleDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeClientDto;
import com.Guesmi.gestion.du.stock.dto.LigneCommandeFournisseurDto;
import com.Guesmi.gestion.du.stock.dto.LigneVenteDto;
import com.Guesmi.gestion.du.stock.exception.EntityNotFoundException;
import com.Guesmi.gestion.du.stock.exception.ErrorCodes;
import com.Guesmi.gestion.du.stock.exception.InvalidEntityException;
import com.Guesmi.gestion.du.stock.exception.InvalidOperationException;
import com.Guesmi.gestion.du.stock.model.LigneCommandeClient;
import com.Guesmi.gestion.du.stock.model.LigneCommandeFournisseur;
import com.Guesmi.gestion.du.stock.model.LigneVente;
import com.Guesmi.gestion.du.stock.repository.ArticleRepository;
import com.Guesmi.gestion.du.stock.repository.LigneCommandeClientRepository;
import com.Guesmi.gestion.du.stock.repository.LigneCommandeFournisseurRepository;
import com.Guesmi.gestion.du.stock.repository.LigneVenteRepository;
import com.Guesmi.gestion.du.stock.services.ArticleService;
import com.Guesmi.gestion.du.stock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

  private ArticleRepository articleRepository;


  @Autowired
  public ArticleServiceImpl(
      ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;

  }

  @Override
  public ArticleDto save(ArticleDto dto) {
    List<String> errors = ArticleValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }

    return ArticleDto.fromEntity(
        articleRepository.save(
            ArticleDto.toEntity(dto)
        )
    );
  }

  @Override
  public ArticleDto findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return null;
    }

    return articleRepository.findById(id).map(ArticleDto::fromEntity).orElseThrow(() ->
        new EntityNotFoundException(
            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ARTICLE_NOT_FOUND)
    );
  }

  @Override
  public ArticleDto findByCodeArticle(String codeArticle) {
    if (!StringUtils.hasLength(codeArticle)) {
      log.error("Article CODE is null");
      return null;
    }

    return articleRepository.findArticleByCodeArticle(codeArticle)
        .map(ArticleDto::fromEntity)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
  }

  @Override
  public List<ArticleDto> findAll() {
    return articleRepository.findAll().stream()
        .map(ArticleDto::fromEntity)
        .collect(Collectors.toList());
  }



  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return;
    }

    articleRepository.deleteById(id);
  }
}
