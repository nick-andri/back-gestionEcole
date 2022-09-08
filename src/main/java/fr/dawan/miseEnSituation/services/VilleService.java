package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.VilleDto;

import java.util.List;

public interface VilleService {

    public List<VilleDto> getAll();
    public List<VilleDto> getAll(int page,int max);
    public VilleDto getById(long id);
    public VilleDto saveOrUpdate(VilleDto v);
    public void deleteById(Long id);
    public List<VilleDto> getAllBySearchPageable(int page,int size,String search);

    public int updateFromDg2() throws Exception;
    public long count();
}
