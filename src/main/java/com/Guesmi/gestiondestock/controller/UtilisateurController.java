package com.Guesmi.gestiondestock.controller;

import com.Guesmi.gestiondestock.controller.api.UtilisateurApi;
import com.Guesmi.gestiondestock.dto.UtilisateurDto;
import com.Guesmi.gestiondestock.services.UtilisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@RestController
@RequestMapping(UTILISATEUR_ENDPOINT)
@Tag(name="Utilisateur-Rest")
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}

