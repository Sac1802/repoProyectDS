package com.example.Proyect_Tequilas_Ardientes.Repository;

import com.example.Proyect_Tequilas_Ardientes.Model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    Information findByIdUser(Long idUser);
}
