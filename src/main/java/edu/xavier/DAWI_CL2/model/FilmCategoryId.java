package edu.xavier.DAWI_CL2.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class FilmCategoryId implements Serializable {
    private Integer categoryId;
    private Integer filmId;

    public FilmCategoryId(Integer categoryId, Integer filmId) {
        this.categoryId = categoryId;
        this.filmId = filmId;
    }

}
