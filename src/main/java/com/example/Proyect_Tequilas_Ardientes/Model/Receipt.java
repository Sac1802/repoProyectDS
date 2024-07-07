package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameUser;
    private String nameProduct;
    private float total;
    private LocalDateTime dateBuy;

    public Receipt(Long id, String nameUser, String nameProduct, float total, LocalDateTime dateBuy) {
        this.id = id;
        this.nameUser = nameUser;
        this.nameProduct = nameProduct;
        this.total = total;
        this.dateBuy = dateBuy;
    }

    public Receipt(){

    }

    public Long getId() {
        return id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDateTime getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalDateTime dateBuy) {
        this.dateBuy = dateBuy;
    }
}
