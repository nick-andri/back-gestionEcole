package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.NiveauDto;

import java.util.List;

public interface NiveauService {
    public List<NiveauDto> getAll();
    public NiveauDto getById(long id);
    public NiveauDto saveOrUpdate(NiveauDto t);
    public void deleteById(long id);

    long count();

    List<NiveauDto> getAll(int page, int size);

    List<NiveauDto> findByNameSearch(int page, int size, String search);
}
