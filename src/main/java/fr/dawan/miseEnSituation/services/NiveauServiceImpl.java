package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.NiveauDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Niveau;
import fr.dawan.miseEnSituation.repositories.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NiveauServiceImpl implements NiveauService{

    @Autowired
    NiveauRepository niveauRepository;

    @Override
    public List<NiveauDto> getAll() {
        return niveauRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, NiveauDto.class)).collect(Collectors.toList());

    }

    @Override
    public NiveauDto getById(long id) {

        return DtoTools.convert(niveauRepository.findById(id).get(),NiveauDto.class);
    }

    @Override
    public NiveauDto saveOrUpdate(NiveauDto b) {
        Niveau bEntity = DtoTools.convert(b, Niveau.class);

        return DtoTools.convert(niveauRepository.saveAndFlush(bEntity),NiveauDto.class);
    }

    @Override
    public void deleteById(long id) {
        niveauRepository.deleteById(id);
    }

    @Override
    public long count() {
        return niveauRepository.count();
    }

    @Override
    public List<NiveauDto> getAll(int page, int size) {
        return niveauRepository.findAll(PageRequest.of(page, size)).stream()
                .map(niveau -> DtoTools.convert(niveau,NiveauDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<NiveauDto> findByNameSearch(int page, int size, String search) {
        return niveauRepository.findAllByDescriptionContaining(PageRequest.of(page, size),search)
                .stream().map(niveau -> DtoTools.convert(niveau,NiveauDto.class)).collect(Collectors.toList());
    }
}
