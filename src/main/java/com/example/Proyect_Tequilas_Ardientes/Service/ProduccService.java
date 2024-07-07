package com.example.Proyect_Tequilas_Ardientes.Service;

import com.example.Proyect_Tequilas_Ardientes.Model.Product;
import com.example.Proyect_Tequilas_Ardientes.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduccService {

    private ProductRepository productRepository;
    public ProduccService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProductsBySellerName(String sellerName) {
        return productRepository.findBySeller(sellerName);
    }

    @Lazy
    public void updateStock(int stock, Long id){
        productRepository.updateProductStock(stock, id);
    }
}
