package fr.dawan.miseEnSituation.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.miseEnSituation.entities.BlocCompetence;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BlocCompetenceRepository extends JpaRepository<BlocCompetence, Long> {

	List<BlocCompetence> findByTitreProfessionnel_TitreContainingIgnoreCase(String texte);
	List<BlocCompetence> findByTitreProfessionnelId(long id);

	List<BlocCompetence>findAllByTitreContaining(Pageable p, String search);
}
