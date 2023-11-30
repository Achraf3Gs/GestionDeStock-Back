package com.Guesmi.gestion.du.stock.repository;


import java.util.List;
import java.util.Optional;

import com.Guesmi.gestion.du.stock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

  Optional<Article> findArticleByCodeArticle(String codeArticle);

  List<Article> findAllByCategoryId(Integer idCategory);


}
