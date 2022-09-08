package fr.dawan.miseEnSituation.services;

import java.util.List;

import fr.dawan.miseEnSituation.dto.EpreuveDto;


public interface EpreuveService {

    public List<EpreuveDto> getAll();
    public List<EpreuveDto> getAll(int page, int max);
    public EpreuveDto getById(long id);
    public EpreuveDto saveOrUpdate(EpreuveDto e);

    void deleteById(long id);
}
