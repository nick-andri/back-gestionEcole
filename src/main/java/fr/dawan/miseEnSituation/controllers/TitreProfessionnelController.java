package fr.dawan.miseEnSituation.controllers;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.dawan.miseEnSituation.dto.BlocCompetenceDto;
import fr.dawan.miseEnSituation.dto.CountDto;
import fr.dawan.miseEnSituation.services.BlocCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.dawan.miseEnSituation.dto.TitreProfessionnelDto;
import fr.dawan.miseEnSituation.services.TitreProfessionnelService;


@RestController
@RequestMapping("/titrePro")
public class TitreProfessionnelController {

    @Autowired
    TitreProfessionnelService titreProfessionnelService;

    @Autowired
    BlocCompetenceService blocCompetenceService;

//////// get ///////

    @GetMapping(value ="/{page}/{size}",produces ="application/json")
    public List<TitreProfessionnelDto> getAllPageable(
            @PathVariable("page") int page,
            @PathVariable("size") int size) {

        return titreProfessionnelService.getAll(page,size);
    }

    @GetMapping(produces = "application/json")
    public List<TitreProfessionnelDto> getAllTitrePro(){
        return titreProfessionnelService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public TitreProfessionnelDto getById(@PathVariable("id") Long id ){

        return titreProfessionnelService.getById(id);
    }

    @GetMapping(value = "/searchByTitle/{search}", produces ="application/json")
    public List<TitreProfessionnelDto> getTitreByTextt(@PathVariable("search") String search){
        return titreProfessionnelService.findByTitreContainingIgnoreCase(search);
    }

    @GetMapping(value ="/name/{page}/{size}/{search}",produces ="application/json")
    public List<TitreProfessionnelDto> getTitreByNamePageable(
                @PathVariable("page") int page,
                @PathVariable("size") int size,
                @PathVariable("search") String search) throws Exception {

        return titreProfessionnelService.getAll(page,size,search);
    }

    /**
     * 
     * @param id
     * @return List de Bloc competence correspondant au titre
     * @throws Exception
     */
    @GetMapping(value = "/{id}/blocs-competences", produces = "application/json")
    public List<BlocCompetenceDto> findBlocCompetencesByTitreId(@PathVariable("id") long id) throws Exception{
        return blocCompetenceService.findByTitreProfessionnelId(id);
    }


    @GetMapping(value = "/count",produces = "application/json")
    public long getTitreCount(){
        return titreProfessionnelService.getTitreCount();
    }

////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<TitreProfessionnelDto> save(@RequestBody TitreProfessionnelDto t){

        TitreProfessionnelDto result = titreProfessionnelService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public TitreProfessionnelDto update(@RequestBody TitreProfessionnelDto t){

        return titreProfessionnelService.saveOrUpdate(t);
    }
/////////// DELETE///

    @DeleteMapping(value = "/{id}")//ok
    public ResponseEntity<Long> delete(@PathVariable Long id){
        titreProfessionnelService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


    @GetMapping(value = "/update-from-dg2", produces = "application/json")
    public CountDto updateFromDG2() throws Exception {
        int nb = titreProfessionnelService.importFromDG2();
        CountDto result = new CountDto();
        result.setNb(nb);
        return result;
    }

    @GetMapping(value = "/{id}/fiche", produces = "application/octet-stream")
    public ResponseEntity<Resource> generateDocById(@PathVariable("id") long id) throws Exception {

        String outputPdfPath = titreProfessionnelService.generatePdf(id);

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
