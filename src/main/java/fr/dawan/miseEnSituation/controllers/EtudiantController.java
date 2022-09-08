package fr.dawan.miseEnSituation.controllers;

import fr.dawan.miseEnSituation.dto.EtudiantDto;
import fr.dawan.miseEnSituation.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Etudiant")
public class EtudiantController {

    @Autowired
    EtudiantService etudiantService;

    //////// get ///////
    @GetMapping(produces = "application/json")
    public List<EtudiantDto> getAll(){

        return etudiantService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public EtudiantDto getById(@PathVariable("id") long id ){

        return etudiantService.getById(id);
    }

    @GetMapping(value = "/{page}/{size}/promo/{promoId}",produces = "application/json")
    public List<EtudiantDto> getAllByPromoByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable("promoId") int promoId){
        return etudiantService.getAllByPomoByPage(page,size,promoId);
    }


    @GetMapping(value= "/count",produces = "application/json")
    public long getCountAll(){
        return etudiantService.countAll();
    }

    @GetMapping(value = "/{page}/{size}",produces = "application/json")
    public List<EtudiantDto> getAllByPage(@PathVariable("page") int page,@PathVariable("size") int size) {
        return etudiantService.getAllByPage(page, size);
    }
    //TODO: les moyenne


    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<EtudiantDto> save(@RequestBody EtudiantDto t) throws Exception{

        EtudiantDto result = etudiantService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public EtudiantDto update(@RequestBody EtudiantDto u) throws Exception{
            return etudiantService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        etudiantService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
