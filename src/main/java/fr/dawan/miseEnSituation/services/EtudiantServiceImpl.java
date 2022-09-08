package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.Tools.HashTools;
import fr.dawan.miseEnSituation.dto.EtudiantDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Etudiant;
import fr.dawan.miseEnSituation.entities.Promotion;
import fr.dawan.miseEnSituation.entities.Utilisateur;
import fr.dawan.miseEnSituation.repositories.EtudiantRepository;
import fr.dawan.miseEnSituation.repositories.PromotionRepository;
import fr.dawan.miseEnSituation.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class EtudiantServiceImpl implements EtudiantService{

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public List<EtudiantDto> getAll() {
        return etudiantRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, EtudiantDto.class)).collect(Collectors.toList());

    }

    @Override
    public EtudiantDto getById(long id) {

        return DtoTools.convert(etudiantRepository.findById(id).get(),EtudiantDto.class);
    }


    @Override
    public EtudiantDto saveOrUpdate(EtudiantDto uDto) throws Exception {
        Etudiant etu = DtoTools.convert(uDto, Etudiant.class);

        if (uDto.getPromotionsId() != null) {
            for (long id : uDto.getPromotionsId()) {
                Optional<Promotion> opt = promotionRepository.findById(id);
                if (opt.isPresent()) {
                    Promotion promo = opt.get();
                    etu.getPromotions().add(promo);
                    promo.getEtudiants().add(etu);
                }
                etu.getPromotions().remove(null);

            }
        }

        if (etu.getId() == 0) { // insertion
            etu.setPassword(HashTools.hashSHA512(etu.getPassword()));


        } else { // modif
            EtudiantDto etuInDb = getById(etu.getId());
            if (!etuInDb.getPassword().contentEquals(etu.getPassword())) {
                etu.setPassword(HashTools.hashSHA512(etu.getPassword()));
            }

        }
        etu = etudiantRepository.saveAndFlush(etu);

        return DtoTools.convert(etu, EtudiantDto.class);
    }

    @Override
    public void deleteById(long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Long countAll() {
        return etudiantRepository.count();
    }

    @Override
    public List<EtudiantDto> getAllByPage(int page, int size) {
        return etudiantRepository.findAll(PageRequest.of(page-1,size)).stream()
                .map(etudiant -> DtoTools.convert(etudiant,EtudiantDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> getAllByPomoByPage(int page, int size, int promoId) {
        return etudiantRepository.findAllBy_PromotionId(PageRequest.of(page, size),promoId)
                .stream().map(etudiant -> DtoTools.convert(etudiant,EtudiantDto.class))
                .collect(Collectors.toList());
    }


}
