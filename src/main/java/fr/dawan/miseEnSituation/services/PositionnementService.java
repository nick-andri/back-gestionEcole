package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.PositionnementDto;

import java.awt.print.Pageable;
import java.util.List;

public interface PositionnementService {

    public List<PositionnementDto> getAll();
    public List<PositionnementDto> getAll(int page, int size);
    public PositionnementDto getById(long id);
    public PositionnementDto saveOrUpdate(PositionnementDto t);
    public void deleteById(long id);


    List<PositionnementDto> getAllByEtudiantAndPromo(long etudiantId, long promotionId) throws Exception;

    PositionnementDto getByEtudiantAndIntervention(long etudiantId, long interventionId) throws Exception;

    String generateGridByPromotion(long promotionId) throws Exception;

    long count();
}
