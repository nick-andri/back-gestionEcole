package fr.dawan.miseEnSituation.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.miseEnSituation.entities.Etudiant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    @Query("SELECT COUNT(etu.id) FROM Etudiant etu JOIN etu.promotions promo WHERE promo.id = :promotionId")
    long countEtudiantByPromotionId(@Param("promotionId") long promotionId);

    Page<Etudiant> findAll(Pageable p);
    long count();
    @Query("SELECT e FROM Etudiant as e JOIN e.promotions promo WHERE promo.id = :promotionId")
    Page<Etudiant>findAllBy_PromotionId(Pageable p,@Param("promotionId") long promoId);

}