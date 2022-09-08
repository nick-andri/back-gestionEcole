package fr.dawan.miseEnSituation.services;


import fr.dawan.miseEnSituation.dto.BlocCompetenceDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.BlocCompetence;
import fr.dawan.miseEnSituation.repositories.BlocCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlocCompetenceServiceImpl implements BlocCompetenceService {

    @Autowired
    BlocCompetenceRepository blocCompetenceRepository;

    @Override
    public List<BlocCompetenceDto> getAll() {
        return blocCompetenceRepository.findAll().stream()
                .map(b -> DtoTools.convert(b, BlocCompetenceDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<BlocCompetenceDto> getAll(int page, int max) {

        return blocCompetenceRepository.findAll(PageRequest.of(page-1, max))
                .stream()
                .map(blocCompetence -> DtoTools.convert(blocCompetence,BlocCompetenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BlocCompetenceDto> getAll(int page, int max,String search) {

        return blocCompetenceRepository.findAllByTitreContaining(PageRequest.of(page-1, max),search)
                .stream()
                .map(blocCompetence -> DtoTools.convert(blocCompetence,BlocCompetenceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BlocCompetenceDto getById(long id) {
        return DtoTools.convert(blocCompetenceRepository.findById(id).get(),BlocCompetenceDto.class);
    }

    @Override
    public BlocCompetenceDto saveOrUpdate(BlocCompetenceDto b) {
        BlocCompetence bEntity = DtoTools.convert(b, BlocCompetence.class);

        return DtoTools.convert(blocCompetenceRepository.saveAndFlush(bEntity),BlocCompetenceDto.class);
    }

    @Override
    public void deleteById(Long id) {
        blocCompetenceRepository.deleteById(id);
    }

    @Override
    public List<BlocCompetenceDto> findByTitrePro_Titre(String texte) {
        return blocCompetenceRepository.findByTitreProfessionnel_TitreContainingIgnoreCase(texte)
                .stream().map(b -> DtoTools.convert(b,BlocCompetenceDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BlocCompetenceDto> findByTitreProfessionnelId(long id) {

        return blocCompetenceRepository.findByTitreProfessionnelId(id).stream()
                .map(b -> DtoTools.convert(b,BlocCompetenceDto.class)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return blocCompetenceRepository.count();
    }
}
