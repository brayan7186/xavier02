package edu.xavier.DAWI_CL2.service;






import edu.xavier.DAWI_CL2.dto.FilmDetailDto;
import edu.xavier.DAWI_CL2.dto.FilmDto;

import java.util.List;

public interface FilmService {

    List<FilmDto> findAll();
    FilmDetailDto findById(Integer id);
    Boolean update(FilmDetailDto filmDetailDto);
    Boolean delete(Integer id);
    Boolean save(FilmDetailDto filmDetailDto);

}
