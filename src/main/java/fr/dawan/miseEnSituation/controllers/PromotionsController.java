package fr.dawan.miseEnSituation.controllers;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import fr.dawan.miseEnSituation.dto.PromotionDto;

import fr.dawan.miseEnSituation.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Promo")
public class PromotionsController {


    @Autowired
    PromotionService promotionService;

    //////// get ///////

    @GetMapping(produces = "application/json")
    public List<PromotionDto> getAll(){

        return promotionService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public PromotionDto getById(@PathVariable("id") Long id){

        return promotionService.getById(id);
    }

    @GetMapping(value = "/{page}/{size}",produces = "application/json")
    public List<PromotionDto> getAll(@PathVariable("page") int page,@PathVariable("size") int size){

        return promotionService.getAll(page, size);
    }

    @GetMapping(value = "/{page}/{size}/{search}",produces = "application/json")
    public List<PromotionDto> getAll(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("search") String search){

        return promotionService.getAll(page,size,search);
    }

    @GetMapping(value="/count",produces = "application/json")
    public long countAll(){
        return promotionService.count();
    }

    /////// post ///////////

    @PostMapping(consumes = "application/json",produces = "application/json")
    ResponseEntity<PromotionDto> create(@RequestBody PromotionDto p){
        PromotionDto result = null;
        try {
            result = promotionService.saveOrUpdate(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    /////// put ////////////


    @PutMapping(consumes = "application/json",produces = "application/json")
    PromotionDto update(@RequestBody PromotionDto upComp) throws Exception {

          return  promotionService.saveOrUpdate(upComp);
    }

    ////////// delete //////////


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        promotionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
