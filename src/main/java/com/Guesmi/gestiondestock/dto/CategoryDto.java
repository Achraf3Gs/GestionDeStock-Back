package com.Guesmi.gestiondestock.dto;


import com.Guesmi.gestiondestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    //Category -> CategoryDto
    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            //TODO throw an exception
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }
    //Category <- CategoryDto
    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            //TODO throw an exception
            return null;
        }
        Category category= new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEntreprise());

         return category;

    }
}

