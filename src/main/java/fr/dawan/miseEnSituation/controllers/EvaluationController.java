package fr.dawan.miseEnSituation.controllers;


import fr.dawan.miseEnSituation.dto.EvaluationDto;
import fr.dawan.miseEnSituation.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/Evaluation")
public class EvaluationController {
    
     @Autowired
    EvaluationService evaluationService;


     ////// GET/////


    @GetMapping(produces = "application/json")
    public List<EvaluationDto> getAll(){

        return evaluationService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public EvaluationDto getById(@PathVariable("id") long id ){

        return evaluationService.getById(id);
    }

    @GetMapping(value = "/etudiant/{id}")
    public List<EvaluationDto> findByEtudiantId(@PathVariable("id") long id){

        return evaluationService.findAllByEtudiantId(id);
    }

    @GetMapping(value = "/Avg/{etuId}/{promoId}/")
    public double getAVGByPromoAndEtudiantId(@PathVariable("etuID") long etuId,
                                             @PathVariable("promoId") long promoId){

        return evaluationService.getAvgByEtudiantIdAndPromotionId(etuId,promoId);
    }

    @GetMapping(value = "/Avg/{promoId}")
    public double GetAVGByPromo(@PathVariable("promoId") long promoId){
        return evaluationService.getAvgByPromotionId(promoId);
    }






    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<EvaluationDto> save(@RequestBody EvaluationDto t){

        EvaluationDto result = evaluationService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }



    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public EvaluationDto update(@RequestBody EvaluationDto u){

        return evaluationService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        evaluationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping(value = "/pdf/{etudiantId}/{promoId}", produces = "application/octet-stream")
    public ResponseEntity<Resource> generateDocById(@PathVariable("etudiantId") long etudiantId,
                                                    @PathVariable("promoId") long promoId) throws Exception {

        String outputPdfPath = evaluationService.generatePdf(etudiantId,promoId);

        File f = new File(outputPdfPath);
        Path path = Paths.get(f.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fiche.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fiche.pdf");


        return ResponseEntity.ok()
                .headers(headers).contentLength(f.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);


    }

}
