package edu.xavier.DAWI_CL2.repository;

import edu.xavier.DAWI_CL2.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language,Integer> {
}
