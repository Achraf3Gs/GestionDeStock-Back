package com.Guesmi.gestion.du.stock.controller;

import com.Guesmi.gestion.du.stock.controller.api.EntrepriseApi;
import com.Guesmi.gestion.du.stock.dto.EntrepriseDto;
import com.Guesmi.gestion.du.stock.services.EntrepriseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestion.du.stock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/entreprise")
@Tag(name="Entreprise-Rest")
public class EntrepriseController implements EntrepriseApi {
    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
