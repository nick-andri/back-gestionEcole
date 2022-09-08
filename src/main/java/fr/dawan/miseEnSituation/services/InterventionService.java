package fr.dawan.miseEnSituation.services;


import fr.dawan.miseEnSituation.dto.InterventionDto;

import java.util.List;

public interface InterventionService {

    public List<InterventionDto> getAll();
    public InterventionDto getById(long id);
    public InterventionDto saveOrUpdate(InterventionDto t);
    public void deleteById(long id);
}
