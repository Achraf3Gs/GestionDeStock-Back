package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("entreprises")
public interface EntrepriseApi {

    @PostMapping( "/create")
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping( "/{idEntreprise}")
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping( "/all")
    List<EntrepriseDto> findAll();

    @DeleteMapping( "/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);

}
