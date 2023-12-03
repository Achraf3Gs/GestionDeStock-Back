package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api("Ventes")
public interface VentesApi {
    @PostMapping("/create")
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping("/{idVente}")
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping("/{codeVente}")
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping("/all")
    List<VentesDto> findAll();

    @DeleteMapping("/delete/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}

