package com.example.pressarticle;

import com.example.pressarticle.DTOs.ArticleDTO;
import com.example.pressarticle.controller.ArticleController;
import com.example.pressarticle.entities.ArticleEntity;
import com.example.pressarticle.repository.ArticleRepository;
import com.example.pressarticle.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class PressArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PressArticleApplication.class, args);
    }

}