package com.Guesmi.gestion.du.stock.repository;

import com.Guesmi.gestion.du.stock.model.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

  Optional<Category> findCategoryByCode(String code);

}
