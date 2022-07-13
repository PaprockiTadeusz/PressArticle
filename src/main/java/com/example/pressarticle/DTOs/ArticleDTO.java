package com.example.pressarticle.DTOs;

import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleDTO {

    private long id;
    private String content;
    private String title;
    private String magazineName;
    private String authorName;
    private String authorSurname;
    private LocalDateTime date;
}
