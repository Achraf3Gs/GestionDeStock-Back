package com.Guesmi.gestion.du.stock.controller;

import com.Guesmi.gestion.du.stock.controller.api.ArticleApi;
import com.Guesmi.gestion.du.stock.dto.ArticleDto;
import com.Guesmi.gestion.du.stock.services.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Guesmi.gestion.du.stock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/articles")
@Tag(name="Article-Rest")
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService= articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
      articleService.delete(id);
    }
}
