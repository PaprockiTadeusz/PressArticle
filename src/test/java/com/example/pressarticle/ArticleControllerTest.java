package com.example.pressarticle;

import com.example.pressarticle.DTOs.ArticleDTO;
import com.example.pressarticle.entities.ArticleEntity;
import com.example.pressarticle.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Should correctly get single Article")
    void shouldGetSingleArticle() throws Exception {
        ArticleEntity articleEntity = new ArticleEntity(1, "Lorem Ipsum", "Best Cooker in the world", "Best Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));
        articleRepository.save(articleEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Best Cooker in the world"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Lorem Ipsum"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazineName").value("Best Magazine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorName").value("Robert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorSurname").value("Makłowicz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2021-10-01T19:00:00"));
        articleRepository.deleteById(1L);
    }

    @Test
    @DisplayName("Should corectlly add Task")
    void shouldCorreclyAddTask() throws Exception {

        ArticleEntity articleEntity = new ArticleEntity(1, "Lorem Lorem", "Best Fighter", "Worst Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));

        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        articleRepository.deleteById(1L);
    }

    @Test
    @DisplayName("Should correctly update task")
    void shouldUpdateTask() throws Exception {

        ArticleEntity articleEntity = new ArticleEntity(1, "Lorem Lorem", "Best Fighter", "Worst Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));
        articleRepository.save(articleEntity);

        ArticleEntity articleEntity2 = new ArticleEntity(1, "Lorem Ipsum", "Best Driver", "Worst Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));

        mockMvc.perform(MockMvcRequestBuilders.put("/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity2)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        articleRepository.deleteById(1L);
    }

    @Test
    @DisplayName("Should correctly delete article")
    void shouldDeleteArticle() throws Exception {
        ArticleEntity articleEntity = new ArticleEntity(1, "Lorem Lorem", "Best Fighter", "Worst Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));
        articleRepository.save(articleEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Should correctly partially update Article")
    void shouldUpdateArticlePartially() throws Exception {

        ArticleEntity articleEntity = new ArticleEntity(1, "Lorem Lorem", "Best Fighter", "Worst Magazine", "Robert", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));

        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleEntity)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ArticleEntity articleToUpdate = new ArticleEntity(1, "Lorem ", "Worst Fighter", "Worst Magazine", "Bartek", "Makłowicz", LocalDateTime.of(2021, 10, 01, 19, 00));
        mockMvc.perform(MockMvcRequestBuilders.patch("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleToUpdate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Worst Fighter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Lorem "))
                .andExpect(MockMvcResultMatchers.jsonPath("$.magazineName").value("Worst Magazine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorName").value("Bartek"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorSurname").value("Makłowicz"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2021-10-01T19:00:00"));
    }


}
