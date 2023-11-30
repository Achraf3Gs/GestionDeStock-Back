package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.EntrepriseDto;
import com.Guesmi.gestion.du.stock.dto.UtilisateurDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
