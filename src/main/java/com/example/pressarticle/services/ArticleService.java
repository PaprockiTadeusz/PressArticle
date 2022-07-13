package com.example.pressarticle.services;

import com.example.pressarticle.DTOs.ArticleDTO;
import com.example.pressarticle.entities.ArticleEntity;
import com.example.pressarticle.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public Optional<ArticleDTO> getArticleById(long id) {
        return articleRepository.findById(id)
                .map(o -> modelMapper.map(o, ArticleDTO.class));
    }

    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ArticleEntity::getDate).reversed())
                .map(o -> modelMapper.map(o, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    public boolean addArticle(ArticleDTO article) {
        if (articleRepository.findById(article.getId()).isEmpty() && articleRepository.findByTitle(article.getTitle()).isEmpty()) {
            articleRepository.save(modelMapper.map(article, ArticleEntity.class));
            return true;
        }

        return false;
    }

    public Optional<ArticleDTO> editArticle(ArticleDTO articleDTO) {
        articleRepository.save(modelMapper.map(articleDTO, ArticleEntity.class));
        return Optional.of(articleDTO);
    }

    public Optional<ArticleDTO> editArticlePartially(ArticleDTO articleDTO) {
        return articleRepository.findById(articleDTO.getId())
                .map(
                        entity -> {
                            Optional.of(articleDTO.getAuthorName()).ifPresent(entity::setAuthorName);
                            Optional.of(articleDTO.getAuthorSurname()).ifPresent(entity::setAuthorSurname);
                            Optional.of(articleDTO.getDate()).ifPresent(entity::setDate);
                            Optional.of(articleDTO.getContent()).ifPresent(entity::setContent);
                            Optional.of(articleDTO.getMagazineName()).ifPresent(entity::setMagazineName);
                            Optional.of(articleDTO.getId()).ifPresent(entity::setId);
                            return Optional.of(modelMapper.map(entity, ArticleDTO.class));
                        }

                ).orElseGet(Optional::empty);
    }

    public boolean deleteArticle(long id) {
        boolean exists = articleRepository.existsById(id);
        articleRepository.deleteById(id);
        return exists;
    }

    public List<ArticleDTO> findArticlesByWordInContent(String word) {
        return articleRepository.findByContentLike("%" + word + "%")
                .stream()
                .sorted(Comparator.comparing(ArticleEntity::getDate).reversed())
                .map(o -> modelMapper.map(o, ArticleDTO.class))
                .collect(Collectors.toList());
    }
}

