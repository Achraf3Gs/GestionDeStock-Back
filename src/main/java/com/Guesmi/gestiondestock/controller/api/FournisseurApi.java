package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("Fournisseur")
public interface FournisseurApi {
    @PostMapping( "/create")
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping( "/{idFournisseur}")
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping("/all")
    List<FournisseurDto> findAll();

    @DeleteMapping( "/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);

}
