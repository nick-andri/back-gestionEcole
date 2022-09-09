package fr.dawan.miseEnSituation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.miseEnSituation.Tools.*;
import fr.dawan.miseEnSituation.dto.CaptchaDto;
import fr.dawan.miseEnSituation.dto.LoginDto;
import fr.dawan.miseEnSituation.dto.LoginResponseDto;
import fr.dawan.miseEnSituation.dto.UtilisateurDto;

import fr.dawan.miseEnSituation.entities.Utilisateur;
import fr.dawan.miseEnSituation.repositories.EtudiantRepository;
import fr.dawan.miseEnSituation.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
   @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
   
    @Override
    public List<UtilisateurDto> getAll() {
        return utilisateurRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, UtilisateurDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<UtilisateurDto> getAll(int page, int size) {
        return utilisateurRepository.findAll(PageRequest.of(page-1,size)).stream()
                .map(u -> DtoTools.convert(u,UtilisateurDto.class)).collect(Collectors.toList());
    }


    @Override
    public UtilisateurDto getById(long id) {

        return DtoTools.convert(utilisateurRepository.findById(id).get(),UtilisateurDto.class);
    }

    @Override
    public UtilisateurDto saveOrUpdate(UtilisateurDto b) {

        Utilisateur bEntity = DtoTools.convert(b, Utilisateur.class);

        try {

            bEntity.setPassword(HashTools.hashSHA512(b.getPassword()));
        }catch (Exception e) {
            e.printStackTrace();
        }


        return DtoTools.convert(utilisateurRepository.saveAndFlush(bEntity),UtilisateurDto.class);
    }

    @Override
    public void deleteById(long id) {
        utilisateurRepository.deleteById(id);

    }


    @Override
    public LoginResponseDto checkLogin(LoginDto loginDto) throws Exception {

        Utilisateur u = utilisateurRepository.findByEmail(loginDto.getEmail());


        if(u!=null
                && u.getPassword().equals(HashTools.hashSHA512(loginDto.getPassword()))
                && u.isActive()
        ) {
            LoginResponseDto result = DtoTools.convert(u, LoginResponseDto.class);

            //generate JWT TOKEN
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("user_id", u.getId());
            claims.put("user_fullName", u.getPrenom() + " " + u.getNom());
            claims.put("user_role", u.getRole().toString());

            String token = jwtTokenUtil.doGenerateToken(claims , loginDto.getEmail());
            TokenSaver.tokensByEmail.put(u.getEmail(), token);
            //générer le token
            //le sauvegarder côté service pour pouvoir le vérifier lors des prochaines requêtes
            result.setToken(token);
            return result;
        }else
            throw new Exception("Error : invalid credentials !");
    }

    @Override
    public List<UtilisateurDto> findByNameSearch(int page, int size, String search){

        return utilisateurRepository.findByNomContainingIgnoreCase(PageRequest.of(page-1,size),search).stream()
                .map(u -> DtoTools.convert(u,UtilisateurDto.class)).collect(Collectors.toList());
    }

    @Override
    public int countByName(String search) {
        return utilisateurRepository.countAllByNomContainingIgnoreCase(search);
    }

    @Override
    public boolean verifCaptcha(CaptchaDto c) throws JsonProcessingException {
        boolean response ;

        response = CaptchaTools.verifCaptcha(c.getToken());
        return response;
    }

    @Override
    public int getUserCount() {
        return (int) utilisateurRepository.count();
    }


}
