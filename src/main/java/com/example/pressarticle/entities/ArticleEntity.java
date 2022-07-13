package com.example.pressarticle.entities;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name ="articles")
@Builder
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "magazine_name")
    private String magazineName;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_surname")
    private String authorSurname;

    @Column(name = "date")
    private LocalDateTime date;

}
