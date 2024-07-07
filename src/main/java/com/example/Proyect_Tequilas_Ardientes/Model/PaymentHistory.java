package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long IdUser;
    private Long IdReceipt;

    public PaymentHistory(Long id, Long idUser, Long idReceipt) {
        this.id = id;
        IdUser = idUser;
        IdReceipt = idReceipt;
    }
    public PaymentHistory(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return IdUser;
    }

    public void setIdUser(Long idUser) {
        IdUser = idUser;
    }

    public Long getIdReceipt() {
        return IdReceipt;
    }

    public void setIdReceipt(Long idReceipt) {
        IdReceipt = idReceipt;
    }
}
