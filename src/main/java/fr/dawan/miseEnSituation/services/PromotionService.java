package fr.dawan.miseEnSituation.services;


import fr.dawan.miseEnSituation.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    public List<PromotionDto> getAll();
    public List<PromotionDto> getAll(int page,int max);
    public PromotionDto getById(long id);
    public PromotionDto saveOrUpdate(PromotionDto t) throws Exception;
    public void deleteById(Long id);


    List<PromotionDto> getAll(int page, int size, String search);

    long count();
}
