package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MethodPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Date date;
    private String cvv;
    @Column(name = "id_information")
    private Long idInformation;

    public MethodPay(Long id, String number, Date date, String cvv, Long idInformation){
        this.id = id;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
        this.idInformation = idInformation;
    }
    public MethodPay(){

    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Long getIdInformation() {
        return idInformation;
    }

    public void setIdInformation(Long idInformation) {
        this.idInformation = idInformation;
    }

    @Override
    public String toString() {
        return "MethodPay{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", cvv='" + cvv + '\'' +
                ", idInformation=" + idInformation +
                '}';
    }
}
