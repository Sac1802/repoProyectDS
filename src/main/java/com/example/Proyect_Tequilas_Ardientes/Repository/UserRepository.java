package com.example.Proyect_Tequilas_Ardientes.Repository;

import com.example.Proyect_Tequilas_Ardientes.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
