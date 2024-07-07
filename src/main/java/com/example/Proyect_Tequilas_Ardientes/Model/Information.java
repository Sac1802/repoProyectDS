package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String last_name;
    private String cellphone;
    private String city;
    private String country;
    private String address;
    @Column(name = "id_user")
    private Long idUser;

    public  Information(Long id, String name, String last_name, String Cellphone, String city,
    String country, String address, Long idUser){
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.cellphone = Cellphone;
        this.city = city;
        this.country = country;
        this.address = address;
        this.idUser = idUser;
    }

    public Information(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", LastName='" + last_name + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", IdUser=" + idUser +
                '}';
    }
}
