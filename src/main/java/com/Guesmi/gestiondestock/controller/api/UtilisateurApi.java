package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("Utilisateurs")
public interface UtilisateurApi {
    @PostMapping( "/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping( "/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping( "/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping( "/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);

}
