package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.CompetenceDto;

import java.util.List;

public interface CompetenceService {

    public List<CompetenceDto> getAll();
    public List<CompetenceDto> getAll(int page,int max);
    public CompetenceDto getById(long id);
    public CompetenceDto saveOrUpdate(CompetenceDto c);
    public void deleteById(Long id);
    public List<CompetenceDto> findByBlocId(Long id);

}
