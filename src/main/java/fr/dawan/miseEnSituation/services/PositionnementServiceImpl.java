package fr.dawan.miseEnSituation.services;

import fr.dawan.miseEnSituation.dto.PositionnementDto;
import fr.dawan.miseEnSituation.Tools.DtoTools;
import fr.dawan.miseEnSituation.Tools.PdfTools;
import fr.dawan.miseEnSituation.entities.Intervention;
import fr.dawan.miseEnSituation.entities.Positionnement;
import fr.dawan.miseEnSituation.entities.Promotion;
import fr.dawan.miseEnSituation.repositories.PositionnementRepository;
import fr.dawan.miseEnSituation.repositories.PromotionRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PositionnementServiceImpl implements PositionnementService{

    @Autowired
    PositionnementRepository positionnementRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${app.storagefolder}")
    private String storageFolder;

    @Value(value = "${backend.url}")
    private String backEndUrl;

    @Override
    public List<PositionnementDto> getAll() {
        return positionnementRepository.findAll().stream()
                .map(u -> DtoTools.convert(u, PositionnementDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<PositionnementDto> getAll(int page, int size) {
        return positionnementRepository.findAll(PageRequest.of(page-1, size))
                .stream().map(positionnement -> DtoTools.convert(positionnement,PositionnementDto.class)).collect(Collectors.toList());
    }

    @Override
    public PositionnementDto getById(long id) {

        return DtoTools.convert(positionnementRepository.findById(id).get(),PositionnementDto.class);
    }

    @Override
    public PositionnementDto saveOrUpdate(PositionnementDto b) {
        Positionnement bEntity = DtoTools.convert(b, Positionnement.class);

        return DtoTools.convert(positionnementRepository.saveAndFlush(bEntity),PositionnementDto.class);
    }

    @Override
    public void deleteById(long id) {
        positionnementRepository.deleteById(id);
    }


    @Override
    public List<PositionnementDto> getAllByEtudiantAndPromo(long etudiantId, long promotionId) throws Exception {
        List<Positionnement> positionnements = positionnementRepository.getAllByEtudiantAndPromo(etudiantId, promotionId);

        // on transforme le résultat en liste de DTO
        List<PositionnementDto> result = new ArrayList<PositionnementDto>();
        for (Positionnement u : positionnements) {
            result.add(DtoTools.convert(u, PositionnementDto.class));
        }
        return result;
    }

    @Override
    public PositionnementDto getByEtudiantAndIntervention(long etudiantId, long interventionId) throws Exception {
        return DtoTools.convert(positionnementRepository.getByEtudiantAndIntervention(etudiantId, interventionId),
                PositionnementDto.class);
    }

    @Override
    public String generateGridByPromotion(long promotionId) throws Exception {
        Optional<Promotion> promotionOpt = promotionRepository.findById(promotionId);
        Promotion promotion = null;
        if (promotionOpt.isPresent()) {
            promotion = promotionOpt.get();

            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            Template template = freemarkerConfig.getTemplate("grillePromo.ftl");


            Map<String, Object> model = new HashMap<String, Object>();
            model.put("promotion",promotion);
            model.put("backEndUrl",backEndUrl);

            List<Positionnement> positionnementsByPromo = positionnementRepository.findAllByPromotionId(promotionId);
            Map<Intervention, List<Positionnement>> posiByPromoMap = positionnementsByPromo.stream()
                    .collect(Collectors.groupingBy(Positionnement::getIntervention));

            model.put("posiByPromoEntries", posiByPromoMap.entrySet());


			for (Intervention interv : posiByPromoMap.keySet()) {
				System.out.println("formation : " + interv.getFormation().getTitre());
				for (Positionnement pos : posiByPromoMap.get(interv)) {
					System.out.println(pos.getEtudiant().getNom() + " - NivDeb : " + pos.getNiveauDebut().getValeur()
							+ " - NivFin : " + pos.getNiveauFin().getValeur());
				}
				System.out.println("---------");
			}

            // on lui demande d'appliquer le template pour l'objet t (titreProfessionnel)
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            // CONVERSION HTML ================> PDF
            String outputPdf = storageFolder + "/grille-promo-" + promotion.getId() + ".pdf";
            PdfTools.generatePdfFromHtml(outputPdf, htmlContent);

            return outputPdf;
        }

        return null;

    }

    @Override
    public long count() {
        return promotionRepository.count();
    }
}
