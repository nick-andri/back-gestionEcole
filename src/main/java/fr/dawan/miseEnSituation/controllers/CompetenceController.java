package fr.dawan.miseEnSituation.controllers;

import fr.dawan.miseEnSituation.dto.CompetenceDto;
import fr.dawan.miseEnSituation.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Competence")
public class CompetenceController {


    @Autowired
    CompetenceService competenceService;

    //////// get ///////

    @GetMapping(produces = "application/json")
    List<CompetenceDto> getAll(){

        return competenceService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    List<CompetenceDto> getById(@PathVariable Long id){

        return competenceService.getAll();
    }

    /////// post ///////////

    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<CompetenceDto> createBloc(@RequestBody CompetenceDto c){
        CompetenceDto result = competenceService.saveOrUpdate(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    /////// put ////////////


    @PutMapping(consumes = "application/json",produces = "application/json")
    CompetenceDto update(@RequestBody CompetenceDto upComp){

        return competenceService.saveOrUpdate(upComp);

    }

    ////////// delete //////////


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        competenceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
