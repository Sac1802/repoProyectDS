package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.Information;
import com.example.Proyect_Tequilas_Ardientes.Model.MethodPay;
import com.example.Proyect_Tequilas_Ardientes.Model.User;
import com.example.Proyect_Tequilas_Ardientes.Repository.InformationRepository;
import com.example.Proyect_Tequilas_Ardientes.Repository.MethodPayRepository;
import com.example.Proyect_Tequilas_Ardientes.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private InformationRepository informationRepository;
    private MethodPayRepository methodPayRepository;

    public UserController(UserRepository userRepository,
                          InformationRepository informationRepository,
                          MethodPayRepository methodPayRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.informationRepository = informationRepository;
        this.methodPayRepository = methodPayRepository;

    }

    public ResponseEntity<User> saveUser(@RequestBody User user){
        ResponseEntity response = null;
        if (user.getRole().equals("vendedor") || user.getRole().equals("comprador")){
            if (user.getPassword().equals(user.getConfirmPassword())){
                String encoderPassword = this.passwordEncoder.encode(user.getPassword());
                user.setPassword(encoderPassword);
                userRepository.save(user);
                response = ResponseEntity.ok().body(user);
            }else {
                response = ResponseEntity.badRequest().body("Password not marchers");
            }
        }else {
            response = ResponseEntity.badRequest().body("Please enter a your role");
        }
        return response;
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }



    public User updateUser( User user , Long id){
        User response;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            String encode = this.passwordEncoder.encode(user.getPassword());
            User existingUser = optionalUser.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setPassword(encode);
            response = userRepository.save(existingUser);
        }else {
            response = user;
        }
        return response;
    }

    public ResponseEntity<User> deleteUser(String email){
        ResponseEntity response = null;

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()){
            Long idUser = userRepository.findByEmail(email).getId();
            Optional<Information> idDelete = Optional.ofNullable(informationRepository.findByIdUser(idUser));
            if (idDelete.isPresent()){
                Long idInformation = informationRepository.findByIdUser(idUser).getId();
                Optional<MethodPay> deleteMethod=
                        Optional.ofNullable(methodPayRepository.findByIdInformation(idInformation));
                if (deleteMethod.isPresent()){
                    informationRepository.deleteById(idInformation);
                    userRepository.deleteById(idUser);
                    methodPayRepository.deleteById(deleteMethod.get().getId());
                    System.out.println("User, Information and Method pay successfully delete");
                    response = ResponseEntity.ok().build();
                }else {
                    informationRepository.deleteById(idInformation);
                    userRepository.deleteById(idUser);
                    System.out.println("User and Information pay successfully delete");
                    response = ResponseEntity.ok().build();
                }

            }else {
                userRepository.deleteById(idUser);
                System.out.println("User successfully delete");
            }

        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    public void deleteById(Long id){
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()){
            Optional<Information> informationId = Optional.ofNullable
                    (informationRepository.findByIdUser(id));
            if (informationId.isPresent()){
                Long idInformation = informationId.get().getId();
                userRepository.deleteById(id);
                informationRepository.deleteById(idInformation);
                System.out.println("Delete Successfully");
            }
        }
    }

    public ResponseEntity<User> getId(String email){
        Optional<User> optional = Optional.ofNullable(userRepository.findByEmail(email));
        ResponseEntity response;
        if (optional.isPresent()){
            response = ResponseEntity.ok(optional.get());
        }else {
            response = ResponseEntity.ok("No se encontro usuario");
        }
        return response;
    }
}
