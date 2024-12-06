package edu.xavier.DAWI_CL2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategory {

    @EmbeddedId
    @Id
    private FilmCategoryId id;
    @MapsId("filmId")
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private Date lastUpdate;

}
