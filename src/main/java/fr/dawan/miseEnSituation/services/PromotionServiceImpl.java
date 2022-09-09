package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.PromotionDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Etudiant;
import fr.dawan.miseEnSituation.entities.Promotion;
import fr.dawan.miseEnSituation.repositories.EtudiantRepository;
import fr.dawan.miseEnSituation.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public List<PromotionDto> getAll() {
        return promotionRepository.findAll().stream()
                .map(p -> DtoTools.convert(p, PromotionDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<PromotionDto> getAll(int page, int max) {

        return promotionRepository.findAll(PageRequest.of(page-1, max))
                .stream().map(promotion -> DtoTools.convert(promotion,PromotionDto.class)).collect(Collectors.toList());
    }


    @Override
    public PromotionDto getById(long id) {
        return DtoTools.convert(promotionRepository.findById(id).get(),PromotionDto.class);
    }


    @Override
    public PromotionDto saveOrUpdate(PromotionDto pDto) throws Exception {
        Promotion promo = DtoTools.convert(pDto, Promotion.class);
        if(pDto.getEtudiantsId()!=null) {
            for (long id : pDto.getEtudiantsId()) {
                Optional<Etudiant> opt = etudiantRepository.findById(id);
                if(opt.isPresent()){
                    Etudiant etu = opt.get();
                    promo.getEtudiants().add(etu);
                    etu.getPromotions().add(promo);
                }
                promo.getEtudiants().remove(null);
            }
        }
        promo = promotionRepository.saveAndFlush(promo);
        return DtoTools.convert(promo, PromotionDto.class);
    }

    @Override
    public void deleteById(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<PromotionDto> getAll(int page, int size, String search) {
        return promotionRepository.findAllByVille_NomContaining(PageRequest.of(page-1, size),search)
                .stream().map(promotion -> DtoTools.convert(promotion,PromotionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return promotionRepository.count();
    }
}
