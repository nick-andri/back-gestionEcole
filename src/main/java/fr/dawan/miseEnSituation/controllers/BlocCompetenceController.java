package fr.dawan.miseEnSituation.controllers;


import fr.dawan.miseEnSituation.dto.BlocCompetenceDto;
import fr.dawan.miseEnSituation.dto.EtudiantDto;
import fr.dawan.miseEnSituation.services.BlocCompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BlocCompetence")
public class BlocCompetenceController {

    @Autowired
    BlocCompetenceService blocCompetenceService;


    //////// get ///////

    @GetMapping(produces = "application/json")
    List<BlocCompetenceDto> getAll(){

        return blocCompetenceService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    BlocCompetenceDto getById(@PathVariable Long id){

        return blocCompetenceService.getById(id);
    }


    @GetMapping(value = "/{page}/{size}",produces = "application/json")
    public List<BlocCompetenceDto> getAllByPage(@PathVariable("page") int page, @PathVariable("size") int size){
        return blocCompetenceService.getAll(page,size);
    }


    /////// post ///////////

    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<BlocCompetenceDto> createBloc(@RequestBody BlocCompetenceDto newBloc){
        BlocCompetenceDto result = blocCompetenceService.saveOrUpdate(newBloc);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    /////// put ////////////


    @PutMapping(consumes = "application/json",produces = "application/json")
    BlocCompetenceDto update(@RequestBody BlocCompetenceDto upBloc){

        return blocCompetenceService.saveOrUpdate(upBloc);

    }

    ////////// delete //////////


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        blocCompetenceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
