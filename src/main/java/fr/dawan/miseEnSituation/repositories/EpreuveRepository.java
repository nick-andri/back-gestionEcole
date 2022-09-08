package fr.dawan.miseEnSituation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.miseEnSituation.entities.Epreuve;
import org.springframework.stereotype.Repository;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {

}