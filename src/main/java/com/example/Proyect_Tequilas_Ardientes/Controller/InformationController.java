package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.Information;
import com.example.Proyect_Tequilas_Ardientes.Repository.InformationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InformationController {
    private final InformationRepository informationRepository;

    public InformationController(InformationRepository informationRepository){
        this.informationRepository = informationRepository;
    }

    @PostMapping("/save/information")
    @Transactional
    public String saveInformation(@RequestBody Information information){
        informationRepository.save(information);
        return "Information save";
    }

    @GetMapping("/get/information")
    public List<Information> getInformation(){
        return informationRepository.findAll();
    }


    public Information getInformationId(Long id){
        return informationRepository.findByIdUser(id);
    }

    @PutMapping("/update/information/{id}")
    public ResponseEntity<Information> updateInformation(@PathVariable Long id,@RequestBody Information
                                                         information){
        ResponseEntity response;
        Optional<Information> optionalInformation = informationRepository.findById(id);
        if (optionalInformation.isPresent()){
            System.out.println(informationRepository.findById(id));
            Information informationUpdate = optionalInformation.get();
            informationUpdate.setName(information.getName());
            informationUpdate.setLast_name(information.getLast_name());
            informationUpdate.setCellphone(information.getCellphone());
            informationUpdate.setCity(information.getCity());
            informationUpdate.setCountry(information.getCountry());
            informationUpdate.setAddress(information.getAddress());
            informationUpdate.setIdUser(information.getIdUser());
            informationRepository.save(informationUpdate);
            response = ResponseEntity.ok("Information Successfully update");
        }else {
            response = ResponseEntity.badRequest().body("User information not found");
        }
        return response;
    }

}
