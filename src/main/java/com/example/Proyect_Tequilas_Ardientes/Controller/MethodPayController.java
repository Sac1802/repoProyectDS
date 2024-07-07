package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.MethodPay;
import com.example.Proyect_Tequilas_Ardientes.Repository.MethodPayRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MethodPayController {

    private final MethodPayRepository methodPayRepository;
    private final PasswordEncoder passwordEncoder;

    public MethodPayController(MethodPayRepository methodPayRepository){
        this.methodPayRepository = methodPayRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String saveMethodPay(MethodPay methodPay){
        String encodeNumber = passwordEncoder.encode(methodPay.getNumber());
        String encodeCvv = passwordEncoder.encode(methodPay.getCvv());
        methodPay.setNumber(encodeNumber);
        methodPay.setCvv(encodeCvv);
        methodPayRepository.save(methodPay);
        return "Information save Successfully";
    }
    public MethodPay getMethodPay(Long id){
        MethodPay response;
        Optional<MethodPay> getMethodPay =
                Optional.ofNullable(methodPayRepository.findByIdInformation(id));
        if (getMethodPay.isPresent()){
            response = getMethodPay.get();
        }else {
            System.out.println("no payment method found");
            response = null;
        }
        return response;
    }

    public String updateMethodPay(Long id, MethodPay methodPay){
        String response;
        Optional<MethodPay> optionalInformation =
                Optional.ofNullable(methodPayRepository.findByIdInformation(id));
        if (optionalInformation.isPresent()){
            MethodPay updateMethodPay = optionalInformation.get();
            String encodeNumber = passwordEncoder.encode(methodPay.getNumber());
            String encodeCvv = passwordEncoder.encode(methodPay.getCvv());
            updateMethodPay.setNumber(encodeNumber);
            updateMethodPay.setDate(methodPay.getDate());
            updateMethodPay.setCvv(encodeCvv);
            response = "Information update Successfully";
        }else {
            response = "Not matches information";
        }
        return response;
    }

    public MethodPay getId(Long idInformation){
        return methodPayRepository.findByIdInformation(idInformation);
    }
}
