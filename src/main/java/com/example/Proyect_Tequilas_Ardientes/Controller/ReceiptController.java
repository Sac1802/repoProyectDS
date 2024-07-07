package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.Receipt;
import com.example.Proyect_Tequilas_Ardientes.Repository.ReceiptRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    private final ReceiptRepository ReceiptRepository;

    public ReceiptController(ReceiptRepository ReceiptRepository) {
        this.ReceiptRepository = ReceiptRepository;
    }

    public ResponseEntity<Receipt> saveReceipt(Receipt receipt){
        ReceiptRepository.save(receipt);
        return ResponseEntity.ok().body(receipt);
    }
}
