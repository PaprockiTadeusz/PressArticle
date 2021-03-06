package com.example.pressarticle.repository;

import com.example.pressarticle.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findById(Long id);
    Optional<ArticleEntity> findByTitle(String topic);
    Optional<ArticleEntity> findByContentLike(String s);
}
