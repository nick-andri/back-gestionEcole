package fr.dawan.miseEnSituation.controllers;

import com.sun.org.apache.xpath.internal.objects.XString;
import fr.dawan.miseEnSituation.Tools.HashTools;
import fr.dawan.miseEnSituation.dto.UtilisateurDto;
import fr.dawan.miseEnSituation.entities.Utilisateur;
import fr.dawan.miseEnSituation.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UtilisateurController {


    @Autowired
    UtilisateurService utilisateurService;

    //////// get ///////
    @GetMapping(produces = "application/json")
    public List<UtilisateurDto> getAll(){

        return utilisateurService.getAll();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public UtilisateurDto getById(@PathVariable("id") long id ){

        return utilisateurService.getById(id);
    }

    @GetMapping(value = "/name/{page}/{size}/{search}",produces ="application/json")
    public List<UtilisateurDto> getByNameSearch(@PathVariable("page") int page,
                                                @PathVariable("size") int size,
                                                @PathVariable("search") String search ){

        return utilisateurService.findByNameSearch(page,size,search);
    }

    @GetMapping(value ="/count/{search}",produces ="application/json")
    public int getCountByName(@PathVariable("search") String search){

        return utilisateurService.countByName(search);
    }

    @GetMapping(value = "/{page}/{size}",produces ="application/json")
    public List<UtilisateurDto> getAllByPage(@PathVariable("page") int page,
                                             @PathVariable("size") int size){
        return utilisateurService.getAll(page,size);
    }

    @GetMapping(value = "/count",produces ="application/json")
    public int getUserCount(){

        return utilisateurService.getUserCount();
    }



    ////// post /////
    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto t){

        UtilisateurDto result = utilisateurService.saveOrUpdate(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping(value = "/checkPass",consumes = "application/json",produces ="application/json")
    public String hashPass(@RequestBody String pass) throws Exception {

        return HashTools.hashSHA512(pass);
    }


    ///// PUT /////////////

    @PutMapping(consumes = "application/json",produces = "application/json")
    public UtilisateurDto update(@RequestBody UtilisateurDto u){

        return utilisateurService.saveOrUpdate(u);
    }
    /////////// DELETE///

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable long id){
        utilisateurService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


}
