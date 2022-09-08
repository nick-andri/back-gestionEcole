package fr.dawan.miseEnSituation.Tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CaptchaTools {


    private static final String URL = "https://www.google.com/recaptcha/api/siteverify";



    private CaptchaTools(){
    }


    public static Boolean verifCaptcha(String token) throws JsonProcessingException {
        Boolean isTokenValid = false;
        ObjectMapper mapper = JsonMapper.builder().build();
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<String> rep = restTemplate.postForEntity(URL,new Object(){public String body=token;},String.class);

        if(rep.getStatusCode()== HttpStatus.OK) {
            String jsonString = rep.getBody();
            APIResponse value = mapper.readValue(jsonString, APIResponse.class);
            isTokenValid=value.getSuccess();
        }

        return isTokenValid;
    }




    static class APIResponse{

        private boolean success;
        private String challenge_ts;  // timestamp of the challenge load (ISO format yyyy-MM-dd'T'HH:mm:ssZZ)
        private String hostname;// the hostname of the site where the reCAPTCHA was solved

       private int[] errorcodes;// optional

        public boolean getSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getChallenge_ts() {
            return challenge_ts;
        }

        public void setChallenge_ts(String challenge_ts) {
            this.challenge_ts = challenge_ts;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public int[] getErrorcodes() {
            return errorcodes;
        }

        public void setErrorcodes(int[] errorcodes) {
            this.errorcodes = errorcodes;
        }

    }
   /* */

}
