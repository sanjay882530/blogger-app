package hc.com.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



//@Service
public class RecaptchService {
	 @Value("${recaptcha.secret.key}")
	    private String recaptchaSecretKey;

	    public boolean verifyRecaptcha(String recaptchaToken) {
	    	 try {
	        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + recaptchaSecretKey + "&response=" + recaptchaToken;
	        RestTemplate restTemplate = new RestTemplate();
	         String response = restTemplate.getForObject(url, String.class);
	        JSONObject jsonResponse = new JSONObject(response);
	       
				return jsonResponse.getBoolean("success");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
	    }

}
