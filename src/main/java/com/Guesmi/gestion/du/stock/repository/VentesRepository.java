package com.Guesmi.gestion.du.stock.repository;


import java.util.Optional;

import com.Guesmi.gestion.du.stock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

  Optional<Ventes> findVentesByCode(String code);
}
