package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.CommandeClientDto;
import com.Guesmi.gestiondestock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@Api("commandeClient")
public interface CommandeClientApi {

    @PostMapping("/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @PatchMapping("/update/etat/{idCommande}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande,
                                                         @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping("/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeClientDto> updateQuantityCommande(@PathVariable("idCommande") Integer idCommande,
                                                             @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                             @PathVariable("quantity") BigDecimal quantite);

    @PatchMapping("/update/client/{idCommande}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande,
                                                             @PathVariable("idClient") Integer idClient);

    @PatchMapping("/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande,
                                                    @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                    @PathVariable("idArticle") Integer newIdArticle);

    @DeleteMapping( "/delete/article/{idCommande}/{idLigneCommande}")
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande,
                                                    @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping( "/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

    @GetMapping( "/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);
    @GetMapping( "/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping( "/delete/{idCommandeClient}")
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}