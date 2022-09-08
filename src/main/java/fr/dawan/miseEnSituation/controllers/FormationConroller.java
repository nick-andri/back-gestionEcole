package fr.dawan.miseEnSituation.controllers;


import fr.dawan.miseEnSituation.dto.FormationDto;
import fr.dawan.miseEnSituation.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Formation")
public class FormationConroller {

    @Autowired
    FormationService formationService;


    ////// GET/////


    @GetMapping(produces = "application/json")
    public List<FormationDto> getAll(){

        return formationService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public FormationDto getById(@PathVariable("id") long id ){

        return formationService.getById(id);
    }
    
    

    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<FormationDto> save(@RequestBody FormationDto t){

        FormationDto result = formationService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public FormationDto update(@RequestBody FormationDto u){

        return formationService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        formationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
