package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.VentesDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

