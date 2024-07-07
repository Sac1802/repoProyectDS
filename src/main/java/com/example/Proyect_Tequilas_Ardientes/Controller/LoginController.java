package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.User;
import com.example.Proyect_Tequilas_Ardientes.Repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/Login")
    @Transactional
    public String login(User user){
        String response;
        User userLogin = userRepository.findByEmail(user.getEmail());
        if (userLogin != null){
            if (passwordEncoder.matches(user.getPassword(), userLogin.getPassword())){
                response = (userLogin.getRole() + "," + userLogin.getId());
            }else {
                response = "The password not mach";
            }
        }else {
            response = "The user enter not found";
        }
        return  response;
    }
}
