package fr.dawan.miseEnSituation.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.miseEnSituation.entities.TitreProfessionnel;

import java.util.List;

@Repository
public interface TitreProfessionnelRepository extends JpaRepository<TitreProfessionnel, Long> {

    List<TitreProfessionnel> findByTitreContainingIgnoreCase(String texte);
    Page<TitreProfessionnel> findAllByTitreContaining(String titre, Pageable pageable);
    Page<TitreProfessionnel> findAll(Pageable pageable);

    long countByTitreContaining(String titre);
    long count();

    @Query("FROM TitreProfessionnel tp WHERE tp.slug= :slug")
    TitreProfessionnel findBySlug(@Param("slug") String slug);
}
