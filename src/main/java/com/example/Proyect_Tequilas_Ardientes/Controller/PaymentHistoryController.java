package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.PaymentHistory;
import com.example.Proyect_Tequilas_Ardientes.Repository.PaymentHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHistoryController {

    private final PaymentHistoryRepository paymentHistoryRepository;

    public PaymentHistoryController(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public ResponseEntity<PaymentHistory> savePaymentHistory(PaymentHistory paymentHistory){
        paymentHistoryRepository.save(paymentHistory);
        return ResponseEntity.ok().body(paymentHistory);
    }
}
