package com.ty.movieService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Movies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private String id;
    private String title;
    private String language;
    private String[] genre;
    private Integer duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
}
