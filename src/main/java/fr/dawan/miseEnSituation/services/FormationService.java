package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.FormationDto;

import java.util.List;

public interface FormationService {
    
    public List<FormationDto> getAll();
    public FormationDto getById(long id);
    public FormationDto saveOrUpdate(FormationDto t);
    public void deleteById(long id);
}
