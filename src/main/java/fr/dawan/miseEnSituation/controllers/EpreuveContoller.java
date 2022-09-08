package fr.dawan.miseEnSituation.controllers;

import fr.dawan.miseEnSituation.dto.EpreuveDto;
import fr.dawan.miseEnSituation.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Epreuve")
public class EpreuveContoller {

    @Autowired
    EpreuveService epreuveService;

    //////// get ///////
    @GetMapping(produces = "application/json")
    public List<EpreuveDto> getAll(){

        return epreuveService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public EpreuveDto getById(@PathVariable("id") long id ){

        return epreuveService.getById(id);
    }

    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<EpreuveDto> save(@RequestBody EpreuveDto t){

        EpreuveDto result = epreuveService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public EpreuveDto update(@RequestBody EpreuveDto u){

        return epreuveService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        epreuveService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
