package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.InterventionDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Intervention;
import fr.dawan.miseEnSituation.repositories.InterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterventionServiceImpl implements  InterventionService{


    @Autowired
    InterventionRepository interventionRepository;

    @Override
    public List<InterventionDto> getAll() {
        return interventionRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, InterventionDto.class)).collect(Collectors.toList());

    }

    @Override
    public InterventionDto getById(long id) {

        return DtoTools.convert(interventionRepository.findById(id).get(),InterventionDto.class);
    }

    @Override
    public InterventionDto saveOrUpdate(InterventionDto b) {
        Intervention bEntity = DtoTools.convert(b, Intervention.class);

        return DtoTools.convert(interventionRepository.saveAndFlush(bEntity),InterventionDto.class);
    }

    @Override
    public void deleteById(long id) {
        interventionRepository.deleteById(id);
    }
}
