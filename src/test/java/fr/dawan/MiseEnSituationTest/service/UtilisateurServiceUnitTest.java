package fr.dawan.MiseEnSituationTest.service;

import fr.dawan.miseEnSituation.dto.UtilisateurDto;
import fr.dawan.miseEnSituation.entities.Utilisateur;
import fr.dawan.miseEnSituation.repositories.UtilisateurRepository;
import fr.dawan.miseEnSituation.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceUnitTest {

    @Mock
    UtilisateurRepository mockRepo;

    @InjectMocks
    UtilisateurService utilisateurService;


    @BeforeEach
    public void intiData(){
/*        List<UtilisateurDto> utilisateurs = new ArrayList<UtilisateurDto>();

        utilisateurs.add(
            new UtilisateurDto(1,"nom","prenom","mail","pass","ADMIN",1,true,"none"));
        utilisateurs.add(
            new UtilisateurDto(2,"nom2","prenom2","mail2","pass2","ADMIN",1,true,"none"));
        utilisateurs.add(
            new UtilisateurDto(3,"nom3","prenom3","mail3","pass3","ETUDIANT",1,true,"none"));
        utilisateurs.add(
            new UtilisateurDto(4,"nom4","prenom4","mail4","pass4","FORMATEUR",1,true,"none"));
        utilisateurs.add(
            new UtilisateurDto(5,"nom5","prenom5","mail5","pass5","ETUDIANT",1,true,"none"));*/
    }

    @Test
    void getAll_Test() {

    }

    @Test
    void pageGetAll_Test() {
    }

    @Test
    void getById_Test() {
    }

    @Test
    void saveOrUpdate_Test() {
    }

    @Test
    void deleteById_Test() {
    }

    @Test
    void checkLogin_Test() {
    }

    @Test
    void findByNameSearch_Test() {
    }

    @Test
    void countByName_Test() {
    }
}