package fr.dawan.miseEnSituation.repositories;

import fr.dawan.miseEnSituation.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {


}