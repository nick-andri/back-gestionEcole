package fr.dawan.miseEnSituation.repositories;


import fr.dawan.miseEnSituation.entities.Utilisateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    @Query("FROM Utilisateur u WHERE u.email= :email")
    Utilisateur findByEmail(@Param("email") String email);

    List<Utilisateur> findByNomContainingIgnoreCase(Pageable page, String search);
    int countAllByNomContainingIgnoreCase(String search);

    Page<Utilisateur> findAll(Pageable p);



}
