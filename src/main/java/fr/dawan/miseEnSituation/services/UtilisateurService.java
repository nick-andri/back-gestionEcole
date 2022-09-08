package fr.dawan.miseEnSituation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.miseEnSituation.dto.CaptchaDto;
import fr.dawan.miseEnSituation.dto.LoginDto;
import fr.dawan.miseEnSituation.dto.LoginResponseDto;
import fr.dawan.miseEnSituation.dto.UtilisateurDto;
import fr.dawan.miseEnSituation.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService 
{
    public List<UtilisateurDto> getAll();
    public List<UtilisateurDto> getAll(int page,int size);
    public UtilisateurDto getById(long id);
    public UtilisateurDto saveOrUpdate(UtilisateurDto u);
    public void deleteById(long id);
    public LoginResponseDto checkLogin(LoginDto login) throws Exception;
    public List<UtilisateurDto> findByNameSearch(int page, int size, String search);
    public int countByName(String search);

    public boolean verifCaptcha(CaptchaDto c) throws JsonProcessingException;
    public int getUserCount();
}
