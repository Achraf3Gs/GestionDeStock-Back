package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.FournisseurDto;
import com.Guesmi.gestion.du.stock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
