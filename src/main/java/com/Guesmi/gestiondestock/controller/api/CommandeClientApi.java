package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("commandeClient")
public interface CommandeClientApi {

    @PostMapping("/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping( "/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

    @GetMapping( "/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);
    @GetMapping( "/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping( "/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);

}