package com.Guesmi.gestiondestock.controller;

import com.Guesmi.gestiondestock.controller.api.ClientApi;
import com.Guesmi.gestiondestock.dto.ClientDto;
import com.Guesmi.gestiondestock.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/clients")
@Tag(name="Client-Rest")
public class ClientController implements ClientApi {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}

