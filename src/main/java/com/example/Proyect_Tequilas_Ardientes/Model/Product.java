package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;
    private float price;
    private int stock;
    private String description;
    private String seller;

    public Product(Long id, String nameProduct, float price, int stock, String description, String
            seller) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.seller = seller;
    }

    public Product(){

    }

    public Long getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}
