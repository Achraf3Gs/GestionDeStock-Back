package com.Guesmi.gestiondestock.controller;

import com.Guesmi.gestiondestock.controller.api.CategoryApi;
import com.Guesmi.gestiondestock.dto.CategoryDto;
import com.Guesmi.gestiondestock.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/categories")
@Tag(name="Category-Rest")
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findById(Integer idCategory) {
        return categoryService.findById(idCategory);
    }

    @Override
    public CategoryDto findByCode(String codeCategory) {
        return categoryService.findByCode(codeCategory);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}

