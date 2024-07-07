package com.example.Proyect_Tequilas_Ardientes.Repository;

import com.example.Proyect_Tequilas_Ardientes.Model.MethodPay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodPayRepository extends JpaRepository<MethodPay, Long> {
    MethodPay findByIdInformation(Long idInformation);
}
