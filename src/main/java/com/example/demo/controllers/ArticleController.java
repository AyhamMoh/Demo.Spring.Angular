package com.example.demo.controllers;


import com.example.demo.dao.ArticleDao;
import com.example.demo.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @GetMapping("/liste-article")
    public List<Article> getListeArticle(){
        return articleDao.findAll();

    }

    @PostMapping("/article")
    public ResponseEntity<Article> ajoutArticle(@RequestBody Article article){


        Optional<Article> articleBDD = articleDao.findByTitre(article.getTitre());
        //un article
        if(articleBDD.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        articleDao.save(article);
       return new ResponseEntity<>(article,HttpStatus.CREATED);
    }
}
