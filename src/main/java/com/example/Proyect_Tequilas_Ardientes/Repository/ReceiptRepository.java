package com.example.Proyect_Tequilas_Ardientes.Repository;

import com.example.Proyect_Tequilas_Ardientes.Model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
