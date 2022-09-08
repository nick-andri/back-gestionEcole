package fr.dawan.miseEnSituation.controllers;

import fr.dawan.miseEnSituation.dto.NiveauDto;
import fr.dawan.miseEnSituation.dto.UtilisateurDto;
import fr.dawan.miseEnSituation.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Niveau")
public class NiveauController {

    @Autowired
    NiveauService niveauService;


    ////// GET/////


    @GetMapping(produces = "application/json")
    public List<NiveauDto> getAll(){

        return niveauService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public NiveauDto getById(@PathVariable("id") long id ){

        return niveauService.getById(id);
    }

    @GetMapping(value = "/count",produces = "application/json")
    public long getCount(){

        return niveauService.count();
    }

    @GetMapping(value = "/name/{page}/{size}/{search}",produces ="application/json")
    public List<NiveauDto> getByNameSearch(@PathVariable("page") int page,
                                                @PathVariable("size") int size,
                                                @PathVariable("search") String search ){

        return niveauService.findByNameSearch(page,size,search);
    }

    @GetMapping(value = "/{page}/{size}",produces ="application/json")
    public List<NiveauDto> getAllByPage(@PathVariable("page") int page,
                                             @PathVariable("size") int size){
        return niveauService.getAll(page,size);
    }
    
    
    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<NiveauDto> save(@RequestBody NiveauDto t){

        NiveauDto result = niveauService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public NiveauDto update(@RequestBody NiveauDto u){

        return niveauService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        niveauService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}