package com.Guesmi.gestiondestock.services.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Guesmi.gestiondestock.dto.*;
import com.Guesmi.gestiondestock.dto.CommandeClientDto;
import com.Guesmi.gestiondestock.dto.LigneCommandeClientDto;
import com.Guesmi.gestiondestock.exception.EntityNotFoundException;
import com.Guesmi.gestiondestock.exception.ErrorCodes;
import com.Guesmi.gestiondestock.exception.InvalidEntityException;
import com.Guesmi.gestiondestock.exception.InvalidOperationException;
import com.Guesmi.gestiondestock.model.*;
import com.Guesmi.gestiondestock.model.Article;
import com.Guesmi.gestiondestock.model.Client;
import com.Guesmi.gestiondestock.model.CommandeClient;
import com.Guesmi.gestiondestock.model.LigneCommandeClient;
import com.Guesmi.gestiondestock.repository.ArticleRepository;
import com.Guesmi.gestiondestock.repository.ClientRepository;
import com.Guesmi.gestiondestock.repository.CommandeClientRepository;
import com.Guesmi.gestiondestock.repository.LigneCommandeClientRepository;
import com.Guesmi.gestiondestock.services.CommandeClientService;
import com.Guesmi.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

  private CommandeClientRepository commandeClientRepository;
  private LigneCommandeClientRepository ligneCommandeClientRepository;
  private ClientRepository clientRepository;
  private ArticleRepository articleRepository;


  @Autowired
  public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                   LigneCommandeClientRepository ligneCommandeClientRepository,
                                   ClientRepository clientRepository, ArticleRepository articleRepository) {
    this.commandeClientRepository = commandeClientRepository;
    this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    this.clientRepository = clientRepository;
    this.articleRepository = articleRepository;
  }

  @Override
  public CommandeClientDto save(CommandeClientDto dto) {
    List<String> errors = CommandeClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Commande client n'est pas valide");
      throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
    }

    Optional<Client> client = clientRepository.findById(dto.getClient().getId());
    if (client.isEmpty()) {
      log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + " n'a ete trouve dans la BDD",
              ErrorCodes.CLIENT_NOT_FOUND);
    }

    List<String> articleErrors = new ArrayList<>();
    if (dto.getLigneCommandeClients() != null) {
      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
        if (ligCmdClt.getArticle() != null) {
          Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
          if (article.isEmpty()) {
            articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
          }
        } else {
          articleErrors.add("Impossible d'enregister une commande avec un article NULL");
        }
      });
    }

    if (!articleErrors.isEmpty()) {
      log.warn("");
      throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
    }

    CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

    if (dto.getLigneCommandeClients() != null) {
      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
        LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
        ligneCommandeClient.setCommandeClient(savedCmdClt);
        ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
        ligneCommandeClientRepository.save(ligneCommandeClient);

      });
    }

    return CommandeClientDto.fromEntity(savedCmdClt);
  }

  @Override
  public CommandeClientDto findById(Integer id) {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return null;
    }
    return commandeClientRepository.findById(id)
            .map(CommandeClientDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
            ));
  }

  @Override
  public CommandeClientDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Commande client CODE is NULL");
      return null;
    }
    return commandeClientRepository.findCommandeClientByCode(code)
            .map(CommandeClientDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucune commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
            ));
  }

  @Override
  public List<CommandeClientDto> findAll() {
    return commandeClientRepository.findAll().stream()
            .map(CommandeClientDto::fromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return;
    }
    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);
    if (!ligneCommandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer une commande client deja utilisee",
              ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
    }
    commandeClientRepository.deleteById(id);
  }
}

