package fr.dawan.miseEnSituation.repositories;

import fr.dawan.miseEnSituation.entities.Promotion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT p FROM Promotion p LEFT JOIN FETCH p.etudiants e")
    List<Promotion> findAll();

    List<Promotion> findAllByVille_NomContaining(Pageable pageable, String search);
}