package hc.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hc.com.model.User;
import hc.com.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
   // @Autowired
   // private RecaptchService recaptchaService;

    public User signUp(User user) {
    	//boolean isRecaptchaValid = recaptchaService.verifyRecaptcha(user.getRecaptchaToken());
		/*
		 * if (!isRecaptchaValid) { throw new
		 * RuntimeException("reCAPTCHA verification failed!"); }
		 */
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists!");
        }
        user.setRole("BLOGGER");
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials!");
        }
        return user;
    }
    public User checkUrl(String username) {
    	 User user = userRepository.findByUsername(username);
    	return user;
    }
}
