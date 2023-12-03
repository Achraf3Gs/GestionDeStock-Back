package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.MvtStkDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
@Api("MvtStkApi")
public interface MvtStkApi {
    @GetMapping("/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping("/mvtstk/filter/article/{idArticle}")
    List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping("/entree")
    MvtStkDto entreeStock(@RequestBody MvtStkDto dto);

    @PostMapping("/sortie")
    MvtStkDto sortieStock(@RequestBody MvtStkDto dto);

    @PostMapping("/correctionpos")
    MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto);

    @PostMapping("/correctionneg")
    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto);

}

