package fr.dawan.miseEnSituation.repositories;

import fr.dawan.miseEnSituation.dto.NiveauDto;
import fr.dawan.miseEnSituation.entities.Niveau;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    List<Niveau> findAllByDescriptionContaining(Pageable p,String search);
}