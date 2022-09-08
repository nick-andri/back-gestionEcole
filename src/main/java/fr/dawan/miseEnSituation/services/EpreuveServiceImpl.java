package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.EpreuveDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Epreuve;
import fr.dawan.miseEnSituation.repositories.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class EpreuveServiceImpl implements EpreuveService{

    @Autowired
    EpreuveRepository epreuveRepository;

    @Override
    public List<EpreuveDto> getAll() {
        return epreuveRepository.findAll().stream()
                .map(b -> DtoTools.convert(b, EpreuveDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<EpreuveDto> getAll(int page, int max) {

        return null;
    }

    @Override
    public EpreuveDto getById(long id) {
        return DtoTools.convert(epreuveRepository.findById(id).get(),EpreuveDto.class);
    }


    @Override
    public EpreuveDto saveOrUpdate(EpreuveDto e) {
        Epreuve bEntity = DtoTools.convert(e, Epreuve.class);

        return DtoTools.convert(epreuveRepository.saveAndFlush(bEntity),EpreuveDto.class);
    }

    @Override
    public void deleteById(long id) {
        epreuveRepository.deleteById(id);
    }
}
