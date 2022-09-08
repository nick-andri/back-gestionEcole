package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.EtudiantDto;

import java.util.List;

public interface EtudiantService {

    public List<EtudiantDto> getAll();

    public EtudiantDto getById(long id);
    public EtudiantDto saveOrUpdate(EtudiantDto e) throws Exception;
    void deleteById(long id);
    Long countAll();
    List<EtudiantDto> getAllByPage(int page, int size);


    List<EtudiantDto> getAllByPomoByPage(int page, int size, int promoId);
}
