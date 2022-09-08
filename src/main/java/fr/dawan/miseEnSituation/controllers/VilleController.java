package fr.dawan.miseEnSituation.controllers;

import fr.dawan.miseEnSituation.dto.CountDto;
import fr.dawan.miseEnSituation.dto.UtilisateurDto;
import fr.dawan.miseEnSituation.dto.VilleDto;
import fr.dawan.miseEnSituation.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ville")
public class VilleController {

    @Autowired
    VilleService villeService;

    //////// get ///////
    @GetMapping(produces = "application/json")
    public List<VilleDto> getAllTitrePro(){
        return villeService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public VilleDto getById(@PathVariable("id") Long id ){

        return villeService.getById(id);
    }

    @GetMapping(value = "/{page}/{size}/{search}",produces ="application/json")
    public List<VilleDto> getByNameSearch(@PathVariable("page") int page,
                                                @PathVariable("size") int size,
                                                @PathVariable("search") String search ) {

        return villeService.getAllBySearchPageable(page, size, search);
    }


    @GetMapping(value = "/{page}/{size}",produces ="application/json")
    public List<VilleDto> getAllByPage(@PathVariable("page") int page,
                                       @PathVariable("size") int size){
        return villeService.getAll(page,size);
    }

    @GetMapping(value = "/count",produces ="application/json")
    public long getCount(){
        return villeService.count();
    }

    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<VilleDto> save(@RequestBody VilleDto t){

        VilleDto result = villeService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public VilleDto update(@RequestBody VilleDto t){

        return villeService.saveOrUpdate(t);
    }
/////////// DELETE///

    @DeleteMapping(value = "/{id}")//ok
    public ResponseEntity<Long> delete(@PathVariable Long id){
        villeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
  ////////////////////
    
    @GetMapping(value="/update-from-dg2", produces = "application/json")
    public CountDto updateFromDG2() throws Exception {
        int nb = villeService.updateFromDg2();
        CountDto result = new CountDto();
        result.setNb(nb);
        return result;
    }
}
