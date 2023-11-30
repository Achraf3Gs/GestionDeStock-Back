package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.ClientDto;
import com.Guesmi.gestion.du.stock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("commandeClient")
public interface CommandeClientApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(@RequestBody CommandeClientDto dto);

    @GetMapping(value = "/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value =  "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> findAll();

    @DeleteMapping(value =  "/delete/{idCommandeClient}")
    void delete(@PathVariable("idCommandeClient") Integer id);

}