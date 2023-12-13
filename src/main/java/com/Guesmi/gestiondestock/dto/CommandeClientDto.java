package com.Guesmi.gestiondestock.dto;

import com.Guesmi.gestiondestock.model.CommandeClient;
import com.Guesmi.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class CommandeClientDto {

    private  Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;


    private List<LigneCommandeClientDto> ligneCommandeClients;

    private Integer idEntreprise;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();

    }

    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto == null) {
            return null;
        }
        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setCode(dto.getCode());
        commandeClient.setClient(ClientDto.toEntity(dto.getClient()));
        commandeClient.setDateCommande(dto.getDateCommande());
        commandeClient.setEtatCommande(dto.getEtatCommande());
        commandeClient.setIdEntreprise(dto.getIdEntreprise());
        return commandeClient;
    }

public  boolean isCommandeLivree(){

        return EtatCommande.LIVREE.equals(this.etatCommande);
}
}
