package com.Guesmi.gestion.du.stock.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeClient")
public class CommandeClient extends AbstractEntity{

    @Column(name="code")
    private String code;

    @Column(name="dateCommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn (name="idclient")
    private Client client;

    @Column(name = "identreprise")
    private Integer idEntreprise;

   @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;


}
