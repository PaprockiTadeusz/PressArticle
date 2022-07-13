package com.example.pressarticle.controller;

import com.example.pressarticle.DTOs.ArticleDTO;
import com.example.pressarticle.services.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllArticles() {
        return new ResponseEntity(articleService.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getArticleById(@PathVariable long id) {
        Optional<ArticleDTO> newArticle = articleService.getArticleById(id);
        return newArticle.map(articleDTO -> new ResponseEntity(articleDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No article with this id " + id, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addArticle(ArticleDTO articleDTO) {
        if (articleService.addArticle(articleDTO)) {
            return new ResponseEntity(articleDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity("There is article with this id: " + articleDTO.getId() + " or this Title " + articleDTO.getTitle(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity editArticle(ArticleDTO articleDTO) {
        return articleService.editArticle(articleDTO)
                .map(article -> new ResponseEntity(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No article with this id", HttpStatus.BAD_REQUEST));
    }

    @PatchMapping
    public ResponseEntity editArticlePartially(ArticleDTO articleDTO) {
        return articleService.editArticlePartially(articleDTO)
                .map(article -> new ResponseEntity(article, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("No article with this id", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping
    public ResponseEntity deleteArticle(ArticleDTO articleDTO) {
        if (articleService.deleteArticle(articleDTO.getId())) {
            return new ResponseEntity("Article has been deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity("Article doesn't exist or already deleted", HttpStatus.BAD_REQUEST);
        }
    }

}
