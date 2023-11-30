package com.Guesmi.gestion.du.stock.controller;

import com.Guesmi.gestion.du.stock.controller.api.CommandeClientApi;
import com.Guesmi.gestion.du.stock.dto.ClientDto;
import com.Guesmi.gestion.du.stock.dto.CommandeClientDto;
import com.Guesmi.gestion.du.stock.services.ClientService;
import com.Guesmi.gestion.du.stock.services.CommandeClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestion.du.stock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/commandeClient")
@Tag(name="CommandeClient-Rest")
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;


    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
