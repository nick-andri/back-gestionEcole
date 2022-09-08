package fr.dawan.miseEnSituation.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.xml.bind.v2.TODO;
import fr.dawan.miseEnSituation.dto.CaptchaDto;
import fr.dawan.miseEnSituation.dto.LoginDto;
import fr.dawan.miseEnSituation.dto.LoginResponseDto;
import fr.dawan.miseEnSituation.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {

	@Autowired
	private UtilisateurService userService;
	
	 @PostMapping(value="/login", consumes="application/json", produces="application/json")
	 public LoginResponseDto checkLogin(@RequestBody LoginDto loginDto) throws Exception {


	    	//appel à la méthode du service

	        return userService.checkLogin(loginDto/*,ajouter captchatoken*/);


	 }


	 @PostMapping(value = "/login/captcha", consumes = "application/json",produces = "application/json")
		public ResponseEntity<Boolean>checkCaptcha(@RequestBody CaptchaDto captchaDto) throws JsonProcessingException {
		Boolean response = userService.verifCaptcha(captchaDto);
	 	return ResponseEntity.status(HttpStatus.OK).body(response);

	 }
}
