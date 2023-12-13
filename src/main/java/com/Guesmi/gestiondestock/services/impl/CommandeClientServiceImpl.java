package com.Guesmi.gestiondestock.services.impl;


import java.math.BigDecimal;
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
import com.Guesmi.gestiondestock.validator.ArticleValidator;
import com.Guesmi.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    if (dto.getId() != null && dto.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorquelle est livree",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
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
  public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    checkIdCommande(idCommande);
    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande  client  is null");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec etat null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);

    commandeClient.setEtatCommande(etatCommande);
    return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
    );
  }

  @Override
  public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
                                                  BigDecimal quantite) {

    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande  is null");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande " +
              "avec une quantite  null ou ZERO",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);

    Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

    LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
    ligneCommandeClient.setQuantite(quantite);
    ligneCommandeClientRepository.save(ligneCommandeClient);
    return commandeClient;
  }

  @Override
  public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
    checkIdCommande(idCommande);
    if (idClient == null) {
      log.error("L'ID du Client is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    Optional<Client> clientOptional = clientRepository.findById(idClient);
    if (clientOptional.isEmpty()) {
      throw new EntityNotFoundException(
              "Aucun client n'a ete trouve avec l'ID" + idClient, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    }

     commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));
    return CommandeClientDto.fromEntity(
            commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
    );
  }




  @Override
  public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    checkIdArticle(idArticle, "nouvel");

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);

    Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

    Optional<Article> articleOptional = articleRepository.findById(idArticle);
    if (articleOptional.isEmpty()) {
      throw new EntityNotFoundException(
              "Aucune article n'a ete trouve avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
    }

    List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
    if (!errors.isEmpty()) {
      throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }

    LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
    ligneCommandeClientToSaved.setArticle(articleOptional.get());
    ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

    return commandeClient;
  }

  @Override
  public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
    //Just to check the LigneCommandeClient and inform the client in case it is absent
     findLigneCommandeClient(idLigneCommande);
    ligneCommandeClientRepository.deleteById(idLigneCommande);
    return commandeClient;
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



  private  void checkIdCommande(Integer idCommande){
    if (idCommande == null) {
      log.error("Commande client ID is null");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec ID null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
  }
  private  void checkIdLigneCommande(Integer idLigneCommande){
    if (idLigneCommande == null) {
      log.error("L'ID de la ligne commande  is null");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande" +
              " avec une ligne de commande null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
  }
  private  void checkIdArticle(Integer idArticle,String msg){
    if (idArticle == null) {
      log.error("L'ID de" +msg+  "is null");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg +
              " ID article null",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
  }

  private  CommandeClientDto CommandeClientDto(Integer idCommande){
    CommandeClientDto commandeClient = findById(idCommande);
    if (commandeClient.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorquelle est livree",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    return commandeClient;
  }

  private CommandeClientDto checkEtatCommande(Integer idCommande) {
    CommandeClientDto commandeClient = findById(idCommande);
    if (commandeClient.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorquelle est livree",
              ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
    }
    return commandeClient;
  }

  private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande){
    Optional<LigneCommandeClient> ligneCommandeClientOptional= ligneCommandeClientRepository.findById(idLigneCommande);
    if (ligneCommandeClientOptional.isEmpty()) {
      throw new EntityNotFoundException(
              "Aucune ligne commande n'a ete trouve avec l'ID" + idLigneCommande, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    }
    return ligneCommandeClientOptional;
  }
}

