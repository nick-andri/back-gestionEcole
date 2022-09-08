package fr.dawan.miseEnSituation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.miseEnSituation.entities.Competence;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long>{


    List<Competence> findAllByBlocCompetenceId(Long id);

}
