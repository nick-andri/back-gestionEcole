package fr.dawan.miseEnSituation.services;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.miseEnSituation.dto.*;
import fr.dawan.miseEnSituation.Tools.PdfTools;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.entities.TitreProfessionnel;
import fr.dawan.miseEnSituation.repositories.TitreProfessionnelRepository;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.transaction.Transactional;


@Transactional
@Service
public class TitreProfessionnelServiceImpl implements TitreProfessionnelService{

    @Autowired
    TitreProfessionnelRepository titreProfessionnelRepository;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private BlocCompetenceService blocCompetencesService;

    @Value("${app.storagefolder}")
    private String storageFolder;

    public String getStorageFolder() {
        return storageFolder;
    }

    public void setStorageFolder(String storageFolder) {
        this.storageFolder = storageFolder;
    }

    @Override
    public List<TitreProfessionnelDto> getAll() {

        List<TitreProfessionnelDto> response = titreProfessionnelRepository.findAll().stream()
                .map(t -> DtoTools.convert(t,TitreProfessionnelDto.class)).collect(Collectors.toList());

    return response;
    }

    @Override
    public List<TitreProfessionnelDto> getAll(int page, int max) {

        return titreProfessionnelRepository.findAll(PageRequest.of(page,max))
                .stream().map(t->DtoTools.convert(t,TitreProfessionnelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TitreProfessionnelDto getById(long id) {
        TitreProfessionnel t = titreProfessionnelRepository.findById(id).get();

      return DtoTools.convert(t,TitreProfessionnelDto.class);

    }



    @Override
    public TitreProfessionnelDto saveOrUpdate(TitreProfessionnelDto t) {
        TitreProfessionnel tEntity = DtoTools.convert(t, TitreProfessionnel.class);

     return DtoTools.convert(titreProfessionnelRepository.saveAndFlush(tEntity),TitreProfessionnelDto.class);
    }

    @Override
    public void deleteById(Long id) {
        titreProfessionnelRepository.deleteById(id);

    }

    @Override
    public List<TitreProfessionnelDto> findByTitreContainingIgnoreCase(String texte) {
        List<TitreProfessionnelDto> response = titreProfessionnelRepository.findByTitreContainingIgnoreCase(texte)
                .stream().map(t -> DtoTools.convert(t,TitreProfessionnelDto.class)).collect(Collectors.toList()) ;
        return response;
    }

    @Override
    public List<TitreProfessionnelDto> getAll(int page, int max, String search) throws Exception {
        // on requête la bdd
        List<TitreProfessionnel> titres = titreProfessionnelRepository.findAllByTitreContaining(search, PageRequest.of(page, max)).get()
                .collect(Collectors.toList());

        // on transforme le résultat en liste de DTO
        List<TitreProfessionnelDto> result = new ArrayList<TitreProfessionnelDto>();
        for (TitreProfessionnel u : titres) {
            result.add(DtoTools.convert(u, TitreProfessionnelDto.class));
        }
        return result;
    }

    @Override
    public CountDto count(String search) {
        long result = titreProfessionnelRepository.countByTitreContaining(search);
        CountDto d = new CountDto();
        d.setNb(result);
        return d;
    }

    @Override
    public long getTitreCount() {
        return titreProfessionnelRepository.count();
    }


    @Override
    public int importFromDG2() throws Exception {
        RestTemplate restTemplate = new RestTemplate();// objet permettant de faire des requêtes HTTP

        ObjectMapper mapper = new ObjectMapper(); // objet de la librairie Jackson permettant de convertir de json>objet
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        ResponseEntity<String> rep = restTemplate
                .getForEntity("https://dawan.org/public/training/search?keywords=titre-professionnel", String.class);// req

        // GET
        int nb = 0;
        if (rep.getStatusCode() == HttpStatus.OK) {

            TypeReference<Map<String, DG2TitreProDto>> typeRef = new TypeReference<Map<String,DG2TitreProDto>>(){};

            Map<String,DG2TitreProDto> results = mapper.readValue(rep.getBody(), typeRef);
            for(String key : results.keySet()) {
                DG2TitreProDto titreProResult = results.get(key);
                DG2TrainingDto trainingObj = titreProResult.getTraining();

                TitreProfessionnel tp = new TitreProfessionnel();
                tp.setTitre(trainingObj.getTitle());
                tp.setSlug(trainingObj.getSlug());

                TitreProfessionnel v = null;
                try {
                    v = titreProfessionnelRepository.findBySlug(tp.getSlug());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (v == null) {
                    titreProfessionnelRepository.saveAndFlush(tp);
                    nb++;
                }
            }
        }
        return nb;
    }

    @Override
    public String generatePdf(long id) throws Exception {

        TitreProfessionnelDto t = getById(id);
        List<BlocCompetenceDto> blocs = blocCompetencesService.findByTitreProfessionnelId(id);

        // on définit ici le chemin où il va chercher les fichiers de templates
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        // charger le template titrepro.ftl et lui envoyer l'objet t
        Template template = freemarkerConfig.getTemplate("titrepro.ftl");

        //Une map pour envoyer plusieurs objets au freemarker template
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("t",t);
        model.put("blocs", blocs);

        // on lui demande d'appliquer le template pour l'objet t (titreProfessionnel)
        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        // CONVERSION HTML ================> PDF
        String outputPdf = storageFolder + "/titrepro-" + t.getId() + ".pdf";
        PdfTools.generatePdfFromHtml(outputPdf, htmlContent);

        return outputPdf;
    }

}
