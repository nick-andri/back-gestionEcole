package fr.dawan.miseEnSituation.services;


import fr.dawan.miseEnSituation.dto.FormationDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Formation;
import fr.dawan.miseEnSituation.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationServiceImpl implements FormationService{

    @Autowired
    FormationRepository formationRepository;

    @Override
    public List<FormationDto> getAll() {
        return formationRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, FormationDto.class)).collect(Collectors.toList());

    }

    @Override
    public FormationDto getById(long id) {

        return DtoTools.convert(formationRepository.findById(id).get(),FormationDto.class);
    }

    @Override
    public FormationDto saveOrUpdate(FormationDto b) {
        Formation bEntity = DtoTools.convert(b, Formation.class);

        return DtoTools.convert(formationRepository.saveAndFlush(bEntity),FormationDto.class);
    }

    @Override
    public void deleteById(long id) {
        formationRepository.deleteById(id);
    }

}
