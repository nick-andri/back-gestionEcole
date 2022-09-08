package fr.dawan.miseEnSituation.services;


import fr.dawan.miseEnSituation.dto.EvaluationDto;

import java.util.List;

public interface EvaluationService {

    public List<EvaluationDto> getAll();
    public EvaluationDto getById(long id);
    public EvaluationDto saveOrUpdate(EvaluationDto t);
    public void deleteById(long id);
    List<EvaluationDto> findAllByEtudiantId(long etudiantId);
    double getAvgByEtudiantIdAndPromotionId(long etudiantId,  long promotionId);
    double getAvgByPromotionId(long promotionId);
    double getAvgByEtudiantIdAndBlocCompId( long etudiantId,long blocCompetencesId);

    public String generatePdf(long etudiantId, long promotionId) throws Exception;
    double getAvgByPromoIdAndBlocCompId(long promoId,long blocCompetencesId);
}
