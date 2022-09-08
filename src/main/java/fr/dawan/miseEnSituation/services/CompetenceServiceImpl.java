package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.CompetenceDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Competence;
import fr.dawan.miseEnSituation.repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenceServiceImpl implements CompetenceService{

    @Autowired
    CompetenceRepository competenceRepository;

    @Override
    public List<CompetenceDto> getAll() {
        return competenceRepository.findAll().stream()
                .map(b -> DtoTools.convert(b, CompetenceDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<CompetenceDto> getAll(int page, int max) {

        return null;
    }

    @Override
    public CompetenceDto getById(long id) {
        return DtoTools.convert(competenceRepository.findById(id),CompetenceDto.class);
    }

    @Override
    public CompetenceDto saveOrUpdate(CompetenceDto c) {
        Competence bEntity = DtoTools.convert(c, Competence.class);

        return DtoTools.convert(competenceRepository.saveAndFlush(bEntity),CompetenceDto.class);
    }

    @Override
    public void deleteById(Long id) {
        competenceRepository.deleteById(id);
    }

    @Override
    public List<CompetenceDto> findByBlocId(Long id) {
        return competenceRepository.findAllByBlocCompetenceId(id).stream().
                map(c -> DtoTools.convert(c,CompetenceDto.class)).collect(Collectors.toList());
    }


}

