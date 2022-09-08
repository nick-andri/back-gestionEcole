package fr.dawan.miseEnSituation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.miseEnSituation.entities.Evaluation;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    //évaluations d'un étudiant donné
    @Query("FROM Evaluation e WHERE e.etudiant.id= :etudiantId")
    List<Evaluation> findAllByEtudiantId(@Param("etudiantId") long etudiantId);

    //moyenne d'un étudiant pour une promotion donnée
    @Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo WHERE etu.id= :etudiantId AND promo.id= :promotionId")
    double getAvgByEtudiantIdAndPromotionId(@Param("etudiantId") long etudiantId, @Param("promotionId") long promotionId);


    //moyenne d'une promo
    @Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo WHERE promo.id= :promotionId")
    double getAvgByPromotionId(@Param("promotionId") long promotionId);


    @Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu WHERE etu.id= :etudiantId AND e.epreuve.blocCompetence.id = :blocCompetencesId")
    double getAvgByEtudiantIdAndBlocCompId(@Param("etudiantId") long etudiantId, @Param("blocCompetencesId") long blocCompetencesId);

    @Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo WHERE promo.id=:promotionId AND e.epreuve.blocCompetence.id=:blocCompetencesId")
    double getAvgByPromotionIdAndBlocCompetencesId(@Param("promotionId")long promotionId, @Param("blocCompetencesId")long blocCompetencesId);
}