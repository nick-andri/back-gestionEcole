package fr.dawan.miseEnSituation.controllers;


import fr.dawan.miseEnSituation.dto.PositionnementDto;
import fr.dawan.miseEnSituation.services.PositionnementService;
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
@RequestMapping("/Pos")
public class PositionnementController {

    @Autowired
    PositionnementService positionnementService;


    ////// GET/////


    @GetMapping(produces = "application/json")
    public List<PositionnementDto> getAll() {

        return positionnementService.getAll();
    }

    @GetMapping(value = "/{page}/{size}",produces = "application/json")
    public List<PositionnementDto> getAll(@PathVariable("page") int page,@PathVariable("size") int size) {

        return positionnementService.getAll(page, size);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PositionnementDto getById(@PathVariable("id") long id) {

        return positionnementService.getById(id);
    }

    @GetMapping(value="/count",produces = "application/json")
    public long countAll(){
        return positionnementService.count();
    }

    ////// post /////
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PositionnementDto> save(@RequestBody PositionnementDto t) {

        PositionnementDto result = positionnementService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json", produces = "application/json")
    public PositionnementDto update(@RequestBody PositionnementDto u) {

        return positionnementService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id) {
        positionnementService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


    @GetMapping(value = { "/by-etu-promo/{etudiantId}/{promotionId}" }, produces = "application/json")
    public List<PositionnementDto> getAllByEtudiantAndPromo(@PathVariable("etudiantId") long etudiantId, @PathVariable("promotionId") long promotionId) throws Exception {
        return positionnementService.getAllByEtudiantAndPromo(etudiantId, promotionId);
    }

    @GetMapping(value = { "/by-etu-intervention/{etudiantId}/{interventionId}" }, produces = "application/json")
    public PositionnementDto getAllByEtudiantAndIntervention(@PathVariable("etudiantId") long etudiantId, @PathVariable("interventionId") long interventionId) throws Exception {
        return positionnementService.getByEtudiantAndIntervention(etudiantId, interventionId);
    }



    @GetMapping(value = "/grille/{promotionId}", produces = "application/octet-stream")
    public ResponseEntity<Resource> generateGridByPromotion(@PathVariable("promotionId") long promotionId) throws Exception {

        String outputPdfPath = positionnementService.generateGridByPromotion(promotionId);

        File f = new File(outputPdfPath);
        Path path = Paths.get(f.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grille-pos-promo"+promotionId+".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }



}