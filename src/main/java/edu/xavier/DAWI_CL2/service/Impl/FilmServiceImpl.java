package edu.xavier.DAWI_CL2.service.Impl;

import edu.xavier.DAWI_CL2.dto.FilmDetailDto;
import edu.xavier.DAWI_CL2.dto.FilmDto;
import edu.xavier.DAWI_CL2.model.Film;
import edu.xavier.DAWI_CL2.model.FilmCategory;
import edu.xavier.DAWI_CL2.model.FilmCategoryId;
import edu.xavier.DAWI_CL2.model.Language;
import edu.xavier.DAWI_CL2.repository.FilmCategoryRepository;
import edu.xavier.DAWI_CL2.repository.FilmRepository;
import edu.xavier.DAWI_CL2.repository.LanguageRepository;
import edu.xavier.DAWI_CL2.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    FilmCategoryRepository filmCategoryRepository;

    @Override
    public List<FilmDto> findAll() {
        List<FilmDto> films= new ArrayList<>();
        try {
            Iterable<Film> iterable=filmRepository.findAll();
            iterable.forEach( film ->{
                FilmDto filmDto=new FilmDto(
                        film.getFilmId(),
                        film.getTitle(),
                        film.getLanguage().getName(),
                        film.getRentalRate()
                );
                //  sacarlo en print
                films.add(filmDto);
                //para mostrar la lista en consola y llamar datos de cache
                String message=String.format("%s-%s;",film.getFilmId(),film.getTitle());
                System.out.print(message);
            });
        }catch (Exception e){
            e.getStackTrace();
        }
        return films;
    }

    @Override
    public FilmDetailDto findById(Integer id) {
        Optional<Film> optional=filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate(),
                        0)
        ).orElse(null);

    }

    @Override
    public Boolean update(FilmDetailDto filmDetailDto) {
        try{
            Optional<Film> optional=filmRepository.findById(filmDetailDto.filmId());
            return optional.map(film ->{
                film.setTitle(filmDetailDto.title());
                film.setDescription(filmDetailDto.description());
                film.setReleaseYear(filmDetailDto.releaseYear());
                film.setRentalDuration(filmDetailDto.rentalDuration());
                film.setRentalRate(filmDetailDto.rentalRate());
                film.setLength(filmDetailDto.length());
                film.setReplacementCost(filmDetailDto.replacementCost());
                film.setRating(filmDetailDto.rating());
                film.setSpecialFeatures(filmDetailDto.specialFeatures());
                film.setLastUpdate(new Date());
                filmRepository.save(film);
                return true;
            }).orElse(null);

        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            Optional<Film> optional=filmRepository.findById(id);
            optional.ifPresentOrElse(
                    film -> {
                        filmRepository.delete(film);
                    },()->{System.out.println("El registro con el Id "+id+" no existe");});
        }catch (Exception e){
            e.getStackTrace();
        }
        return true;
    }

    @Override
    public Boolean save(FilmDetailDto filmDetailDto) {
        try{
            Optional<Language> language = languageRepository.findById(filmDetailDto.languageId());
            if (language.isEmpty()) {
                throw new IllegalArgumentException("Language not found");
            }
            Film film=new Film();
            film.setTitle(filmDetailDto.title());
            film.setDescription(filmDetailDto.description());
            film.setReleaseYear(filmDetailDto.releaseYear());
            film.setLanguage(language.get());
            film.setRentalDuration(filmDetailDto.rentalDuration());
            film.setRentalRate(filmDetailDto.rentalRate());
            film.setLength(filmDetailDto.length());
            film.setReplacementCost(filmDetailDto.replacementCost());
            film.setRating(filmDetailDto.rating());
            film.setSpecialFeatures(filmDetailDto.specialFeatures());
            film.setLastUpdate(new Date());
            Film savedFilm=filmRepository.save(film);
            FilmCategoryId filmCategoryId = new FilmCategoryId(filmDetailDto.categoryId(), savedFilm.getFilmId());
            FilmCategory filmCategory = new FilmCategory();
            filmCategory.setId(filmCategoryId);
            filmCategory.setFilm(savedFilm);
            filmCategory.setLastUpdate(new Date());
            filmCategoryRepository.save(filmCategory);
            return true;
        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }





}
