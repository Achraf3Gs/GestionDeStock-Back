package com.Guesmi.gestiondestock.controller.api;

import com.Guesmi.gestiondestock.dto.ArticleDto;
/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("articles")
/*@Api(APP_ROOT+"/articles")*/
public interface ArticleApi {
    @Operation(
            description = "Enregister un article",
            summary = "Cette methode permet d'enregister ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "L'objet article cree/modifie",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "L'objet article n'est pas valide",
                            responseCode ="400"
                    )
            }
    )
    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto dto);

    @Operation(
            description = "Rechercher un article par ID",
            summary = "Cette methode permet de chercher  un article par son Id",
            responses = {
                    @ApiResponse(
                            description = "L'artictle a ete trouve dans la BDD",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "L'artictle n'existe pas dans la BDD avec l'ID fourni",
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
    @GetMapping (value = "/{idArticle}",
            produces =  MediaType.APPLICATION_JSON_VALUE)

    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @Operation(
            description = "Rechercher un article par CODE",
            summary = "Cette methode permet de chercher  un article par son CODE",
            responses = {
                    @ApiResponse(
                            description = "L'artictle a ete trouve dans la BDD",
                            responseCode ="200"
                    ),
                    @ApiResponse(
                            description = "L'artictle n'existe pas dans la BDD avec l'ID fourni",
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
    @GetMapping (value = "filter/{codeArticle}",
            produces =  MediaType.APPLICATION_JSON_VALUE)

    ArticleDto findByCodeArticle(@PathVariable("codeArticle")String codeArticle);
    @Operation(
            description = "Renvoi la liste des articles",
            summary = "Cette methode permet de chercher  et renvoyer la liste des articles",
            responses = {
                    @ApiResponse(
                            description = "La liste des article / Une liste vide",
                            responseCode ="200"
                    )
            }
    )
    @GetMapping (value = "/all",
            produces =  MediaType.APPLICATION_JSON_VALUE)

    List<ArticleDto> findAll();
    @Operation(
            description = "Supprimer un article ",
            summary = "Cette methode permet de supprimer un article par ID",
            responses = {
                    @ApiResponse(
                            description = "L'article a ete supprime",
                            responseCode ="200"
                    )
            }
    )
   @DeleteMapping( value ="/delete/{idArticle}",
           produces =  MediaType.APPLICATION_JSON_VALUE)

    void delete(@PathVariable("idArticle") Integer id);

}
