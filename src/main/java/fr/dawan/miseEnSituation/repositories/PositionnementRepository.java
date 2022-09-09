package fr.dawan.miseEnSituation.repositories;

import fr.dawan.miseEnSituation.dto.PositionnementDto;
import fr.dawan.miseEnSituation.entities.Positionnement;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionnementRepository extends JpaRepository<Positionnement, Long> {

    @Query("FROM Positionnement p JOIN p.etudiant etu JOIN FETCH etu.promotions promo WHERE etu.id=:etudiantId AND promo.id= :promotionId")
    List<Positionnement> getAllByEtudiantAndPromo(long etudiantId, long promotionId);

    @Query("FROM Positionnement p WHERE p.etudiant.id=:etudiantId AND p.intervention.id= :interventionId")
    Positionnement getByEtudiantAndIntervention(long etudiantId, long interventionId);

    @Query("FROM Positionnement p JOIN p.etudiant etu JOIN FETCH etu.promotions promo WHERE promo.id= :promotionId")
    List<Positionnement> findAllByPromotionId(long promotionId);

}