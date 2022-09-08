package fr.dawan.miseEnSituation.services;

import java.util.List;

import fr.dawan.miseEnSituation.dto.CountDto;
import fr.dawan.miseEnSituation.dto.TitreProfessionnelDto;

public interface TitreProfessionnelService  {

    public List<TitreProfessionnelDto> getAll();
    public List<TitreProfessionnelDto> getAll(int page,int max);
    public List<TitreProfessionnelDto> getAll(int page, int max, String search) throws Exception;
    public TitreProfessionnelDto getById(long id);
    public TitreProfessionnelDto saveOrUpdate(TitreProfessionnelDto t);
    public void deleteById(Long id);
    public List<TitreProfessionnelDto> findByTitreContainingIgnoreCase(String texte);
    int importFromDG2() throws Exception;

    String generatePdf(long id) throws Exception;
    public CountDto count(String search);


    long getTitreCount();
}
