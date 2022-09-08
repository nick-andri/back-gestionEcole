package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.BlocCompetenceDto;


import java.util.List;

public interface BlocCompetenceService {

    public List<BlocCompetenceDto> getAll();
    public List<BlocCompetenceDto> getAll(int page,int max);
    public BlocCompetenceDto getById(long id);
    public BlocCompetenceDto saveOrUpdate(BlocCompetenceDto t);
    public void deleteById(Long id);
    public List<BlocCompetenceDto> getAll(int page, int max,String search);
    List<BlocCompetenceDto> findByTitrePro_Titre(String texte);
    List<BlocCompetenceDto> findByTitreProfessionnelId(long id);
    long count();



}
