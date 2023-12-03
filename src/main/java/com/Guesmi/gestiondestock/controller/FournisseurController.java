package com.Guesmi.gestiondestock.controller;

import com.Guesmi.gestiondestock.controller.api.FournisseurApi;
import com.Guesmi.gestiondestock.dto.FournisseurDto;
import com.Guesmi.gestiondestock.services.FournisseurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

@RestController
@RequestMapping(FOURNISSEUR_ENDPOINT)
@Tag(name="Fournisseur-Rest")
public class FournisseurController implements FournisseurApi {
    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
