package edu.xavier.DAWI_CL2.dto;

public record FilmDto(
        Integer filmId,
        String title,
        String language,
        Double rentalRate
) {
}
