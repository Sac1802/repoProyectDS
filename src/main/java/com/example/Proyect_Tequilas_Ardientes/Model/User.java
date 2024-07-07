package com.example.Proyect_Tequilas_Ardientes.Model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String email;
    private String password;
    private String role;
    @Transient
    private String confirmPassword;
    public User(Long Id, String email, String password, String role, String confirmPassword){
        this.Id = Id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    public User(){

    }

    public Long getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole(){ return role; }


    public void setRole(String role) { this.role = role; }

    public String getConfirmPassword(){ return confirmPassword; }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
