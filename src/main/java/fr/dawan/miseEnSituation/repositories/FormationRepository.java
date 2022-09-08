package fr.dawan.miseEnSituation.repositories;

import fr.dawan.miseEnSituation.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {


}