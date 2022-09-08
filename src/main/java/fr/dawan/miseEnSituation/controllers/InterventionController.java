package fr.dawan.miseEnSituation.controllers;


import fr.dawan.miseEnSituation.dto.InterventionDto;
import fr.dawan.miseEnSituation.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Intervention")
public class InterventionController {

    @Autowired
    InterventionService interventionService;


    ////// GET/////


    @GetMapping(produces = "application/json")
    public List<InterventionDto> getAll(){

        return interventionService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public InterventionDto getById(@PathVariable("id") long id ){

        return interventionService.getById(id);
    }



    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<InterventionDto> save(@RequestBody InterventionDto t){

        InterventionDto result = interventionService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public InterventionDto update(@RequestBody InterventionDto u){

        return interventionService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        interventionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}