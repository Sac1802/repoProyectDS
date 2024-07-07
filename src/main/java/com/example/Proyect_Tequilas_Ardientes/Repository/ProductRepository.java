package com.example.Proyect_Tequilas_Ardientes.Repository;

import com.example.Proyect_Tequilas_Ardientes.Model.Product;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM Product WHERE seller = :seller", nativeQuery = true)
    List<Product> findBySeller(String seller);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Product SET Stock = :stock WHERE id = :id", nativeQuery = true)
    void updateProductStock(int stock, Long id);
}
