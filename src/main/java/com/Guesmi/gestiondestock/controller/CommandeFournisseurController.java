package com.Guesmi.gestiondestock.controller;

import com.Guesmi.gestiondestock.controller.api.CommandeFournisseurApi;
import com.Guesmi.gestiondestock.dto.CommandeFournisseurDto;
import com.Guesmi.gestiondestock.services.CommandeFournisseurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/commandeFournisseur")
@Tag(name="CommandeFournisseur-Rest")
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService commandeFournisseurService;


    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService
                                                     commandeFournisseurService) {
        this.commandeFournisseurService= commandeFournisseurService;
    }

        @Override
        public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
            return commandeFournisseurService.save(dto);
        }

        @Override
        public CommandeFournisseurDto findById(Integer id) {
            return commandeFournisseurService.findById(id);
        }

        @Override
        public List<CommandeFournisseurDto> findAll() {
            return commandeFournisseurService.findAll();
        }

        @Override
        public void delete(Integer id) {
            commandeFournisseurService.delete(id);
        }
    }
