package edu.xavier.DAWI_CL2.repository;

import edu.xavier.DAWI_CL2.model.FilmCategory;
import edu.xavier.DAWI_CL2.model.FilmCategoryId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmCategoryRepository extends CrudRepository<FilmCategory, FilmCategoryId> {
}
