package com.Guesmi.gestion.du.stock.repository;

import com.Guesmi.gestion.du.stock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
