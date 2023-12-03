package com.Guesmi.gestiondestock.services;


import com.Guesmi.gestiondestock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

  CategoryDto save(CategoryDto dto);

  CategoryDto findById(Integer id);

  CategoryDto findByCode(String code);

  List<CategoryDto> findAll();

  void delete(Integer id);

}
