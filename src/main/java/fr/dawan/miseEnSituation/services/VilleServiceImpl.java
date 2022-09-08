package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.DG2LocationDto;
import fr.dawan.miseEnSituation.dto.VilleDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.Ville;
import fr.dawan.miseEnSituation.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VilleServiceImpl implements VilleService{

    @Autowired
    VilleRepository villeRepository;

    @Override
    public long count(){
        return villeRepository.count();
    }

    @Override
    public List<VilleDto> getAll() {
        return villeRepository.findAll().stream()
                .map(b -> DtoTools.convert(b, VilleDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<VilleDto> getAllBySearchPageable(int page,int size,String search) {
        return villeRepository.findAllByNomContaining(search,PageRequest.of(page, size)).stream()
                .map(b -> DtoTools.convert(b, VilleDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<VilleDto> getAll(int page, int size) {

        return villeRepository.findAll(PageRequest.of(page-1, size)).stream()
                .map(v -> DtoTools.convert(v,VilleDto.class)).collect(Collectors.toList());
    }

    @Override
    public VilleDto getById(long id) {

        return DtoTools.convert(villeRepository.findById(id).get(),VilleDto.class);
    }

    @Override
    public VilleDto saveOrUpdate(VilleDto b) {
        Ville bEntity = DtoTools.convert(b, Ville.class);

        return DtoTools.convert(villeRepository.saveAndFlush(bEntity),VilleDto.class);

    }

    @Override
    public void deleteById(Long id) {
        villeRepository.deleteById(id);
    }



    @Override
    public int updateFromDg2() throws Exception{
        RestTemplate restTemplate = new RestTemplate();// objet permettant de faire des requêtes HTTP

        ObjectMapper mapper = new ObjectMapper(); // objet de la librairie Jackson permettant de convertir de json>objet
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        ResponseEntity<String> rep = restTemplate.getForEntity("https://dawan.org/public/location/", String.class);// req

        // GET
        int nb = 0;
        if (rep.getStatusCode() == HttpStatus.OK) {

            DG2LocationDto[] villes = mapper.readValue(rep.getBody(), DG2LocationDto[].class);
            // traitement à faire avec les localisations récupérées
            for (DG2LocationDto locDto : villes) {
                VilleDto vDto = DtoTools.convert(locDto, VilleDto.class);
                // vérifier qu'elles n'existent pas en bdd (comparaison par rapport au slug)
                // puis insertion s'il n'existe pas
                Ville v = null;
                try {
                    v = villeRepository.findBySlug(vDto.getSlug());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (v == null) {
                    saveOrUpdate(vDto);
                    nb++;
                }
            }
        }
        return nb;
    }
}
