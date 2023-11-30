package com.Guesmi.gestion.du.stock.controller.api;

import com.Guesmi.gestion.du.stock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("categories")
public interface CategoryApi {
    @Operation(
            description = "Enregister une categorie",
            summary = "Cette methode permet d'enregister ou modifier une categorie",
            responses = {
                    @ApiResponse(
                            description = "L'objet  categorie cree/modifie",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "L'objet categorie n'est pas valide",
                            responseCode ="400"
                    )
            }
    )
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto);
    @Operation(
            description = "Rechercher une categorie par ID",
            summary = "Cette methode permet de chercher  une categorie par son Id",
            responses = {
                    @ApiResponse(
                            description = "La categorie a ete trouve dans la BDD",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "La categorie n'existe pas dans la BDD avec l'ID fourni",
                            responseCode ="404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode ="401"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode ="403"
                    )
            }
    )
    @GetMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)

    CategoryDto findById( @PathVariable("idCategory") Integer idCategory);
    @Operation(
            description = "Rechercher une categorie par CODE",
            summary = "Cette methode permet de chercher  une categorie par son CODE",
            responses = {
                    @ApiResponse(
                            description = "La categorie a ete trouve dans la BDD",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "La categorie n'existe pas dans la BDD avec l'ID fourni",
                            responseCode ="404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode ="401"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode ="403"
                    )
            }
    )
    @GetMapping(value =  "/filter/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)

    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);
    @Operation(
            description = "Renvoi la liste des articles",
            summary = "Cette methode permet de chercher  et renvoyer la liste des articles",
            responses = {
                    @ApiResponse(
                            description = "La liste  des categories / Une liste vide",
                            responseCode ="200"
                    )
            }
    )
    @GetMapping(value =  "/all", produces = MediaType.APPLICATION_JSON_VALUE)

    List<CategoryDto> findAll();
    @Operation(
            description = "Supprimer une categorie ",
            summary = "Cette methode permet de supprimer une categorie par ID",
            responses = {
                    @ApiResponse(
                            description = "La categorie a ete supprime",
                            responseCode ="200"
                    )
            }
    )
    @DeleteMapping(value ="/delete/{idCategory}")

    void delete(@PathVariable("idCategory") Integer id);

}

