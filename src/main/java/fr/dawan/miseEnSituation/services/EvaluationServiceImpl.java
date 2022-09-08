package fr.dawan.miseEnSituation.services;

import com.sun.javaws.Main;
import fr.dawan.miseEnSituation.dto.*;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.Tools.PdfTools;
import fr.dawan.miseEnSituation.entities.Evaluation;
import fr.dawan.miseEnSituation.repositories.EvaluationRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService{

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    TitreProfessionnelService titreProfessionnelService;

    @Autowired
    BlocCompetenceService blocCompetenceService;

    @Autowired
    CompetenceService competenceService;

    @Autowired
    Configuration freemarkerConfig;

    @Value("${app.storagefolder}")
    private String storageFolder;

    @Value(value = "${backend.url}")
    private String backEndUrl;


    @Override
    public List<EvaluationDto> getAll() {
        return evaluationRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, EvaluationDto.class)).collect(Collectors.toList());

    }

    @Override
    public EvaluationDto getById(long id) {

        return DtoTools.convert(evaluationRepository.findById(id).get(),EvaluationDto.class);
    }

    @Override
    public EvaluationDto saveOrUpdate(EvaluationDto b) {
        Evaluation bEntity = DtoTools.convert(b, Evaluation.class);

        return DtoTools.convert(evaluationRepository.saveAndFlush(bEntity),EvaluationDto.class);
    }

    @Override
    public void deleteById(long id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public List<EvaluationDto> findAllByEtudiantId(long evaluationId) {
        return  evaluationRepository.findAllByEtudiantId(evaluationId).stream()
                .map(e -> DtoTools.convert(e,EvaluationDto.class)).collect(Collectors.toList());
    }

    @Override
    public double getAvgByEtudiantIdAndPromotionId(long evaluationId, long promotionId) {
        return evaluationRepository.getAvgByEtudiantIdAndPromotionId(evaluationId, promotionId);
    }

    @Override
    public double getAvgByPromotionId(long promotionId) {
        return evaluationRepository.getAvgByPromotionId(promotionId);
    }

    @Override
    public double getAvgByEtudiantIdAndBlocCompId(long etudiantId, long blocCompetencesId) {
        return evaluationRepository.getAvgByEtudiantIdAndBlocCompId(etudiantId,blocCompetencesId);
    }

    @Override
    public String generatePdf(long etudiantId, long promotionId) throws Exception {

        EtudiantDto etudiant = etudiantService.getById(etudiantId);
        PromotionDto promotion = promotionService.getById(promotionId);

        long titreProId = promotion.getTitreProfessionnelId();
        TitreProfessionnelDto titrePro = titreProfessionnelService.getById(titreProId);

        List<BlocCompetenceDto> blocs = blocCompetenceService.findByTitreProfessionnelId(titreProId);

        List<EvaluationFrontPdfDto> frontPdfDtos = new ArrayList<>();
        //

        for (BlocCompetenceDto bloc : blocs ){

            EvaluationFrontPdfDto ef = new EvaluationFrontPdfDto();
            ef.setBlocComp(bloc);
            try {
                ef.setMoyEtuByBloc(this.getAvgByEtudiantIdAndBlocCompId(etudiantId,bloc.getId()));
            }catch (Exception e){
                ef.setMoyEtuByBloc(-1.0);
            }

            ef.setCompetences(competenceService.findByBlocId(bloc.getId()));
            try{
                ef.setMoyPromoByBloc(this.getAvgByPromoIdAndBlocCompId(promotionId,bloc.getId()));
            }catch (Exception e){
                ef.setMoyPromoByBloc(-1.0);
            }

            frontPdfDtos.add(ef);
        }


        double moyenneGeneralEtudiant = 0.0;
        double moyenneGeneralPromotion = 0.0;
//


        moyenneGeneralEtudiant = this.getAvgByEtudiantIdAndPromotionId(etudiantId,promotionId );
        moyenneGeneralPromotion = this.getAvgByPromotionId(promotionId);



        Map<String, Object> model = new HashMap<String, Object>();
        model.put("evalDto", frontPdfDtos);
        model.put("backEndUrl",backEndUrl);
        model.put("titrePro", titrePro);
        model.put("etudiant", etudiant);

        model.put("promotion", promotion);
        model.put("frontPdfDtos",frontPdfDtos);

        model.put("moyenneGeneralEtudiant", moyenneGeneralEtudiant);
        model.put("moyenneGeneralPromotion", moyenneGeneralPromotion);


        ///////////////////////////////////////////////////////////////////////

        //on définit ici le chemin où il va chercher les fichiers de templates
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        //charger le template titrepro.ftl et lui envoyer l'objet t
        Template template = freemarkerConfig.getTemplate("evaluation.ftl");

        //on lui demande d'appliquer le template pour l'objet t (titreProfessionnel)
        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        // CONVERSION =================> PDF
        String outputpdf = storageFolder + "/Bulltin d'évaluation" + etudiant.getId() + ".pdf";
        PdfTools.generatePdfFromHtml(outputpdf, htmlContent);

        return outputpdf;
    }

    @Override
    public double getAvgByPromoIdAndBlocCompId(long promoId, long blocCompetencesId) {
        return evaluationRepository.getAvgByPromotionIdAndBlocCompetencesId(promoId,blocCompetencesId) ;
    }
}
