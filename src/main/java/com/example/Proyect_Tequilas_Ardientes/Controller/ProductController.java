package com.example.Proyect_Tequilas_Ardientes.Controller;

import com.example.Proyect_Tequilas_Ardientes.Model.Product;
import com.example.Proyect_Tequilas_Ardientes.Repository.ProductRepository;
import com.example.Proyect_Tequilas_Ardientes.Service.ProduccService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private ProduccService produccService;

    public ProductController(ProductRepository productRepository,
                             ProduccService produccService){
        this.productRepository = productRepository;
        this.produccService = produccService;
    }
    @PostMapping("/save/product")
    @Transactional
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Product response = null;
        Optional<Product> OptionalProduct = productRepository.findById(id);
        if (OptionalProduct.isPresent()){
            response = OptionalProduct.get();
        }else {
            System.out.println("No se encontro el producto");
        }
        return response;
    }

    public ResponseEntity<Product> updateProduct(Long id, Product product){
        ResponseEntity response;
        Optional<Product> OptionalProduct = productRepository.findById(id);
        if (OptionalProduct.isPresent()){
            Product existProduct = OptionalProduct.get();
            existProduct.setNameProduct(product.getNameProduct());
            existProduct.setPrice(product.getPrice());
            existProduct.setStock(product.getStock());
            existProduct.setDescription(product.getDescription());
            existProduct.setSeller(product.getSeller());
            productRepository.save(existProduct);
            response = ResponseEntity.ok(product);
        }else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }


    public ResponseEntity<Product> deleteProduct(Long id){
        ResponseEntity response;
        Optional<Product> OptionalProduct = productRepository.findById(id);
        if (OptionalProduct.isPresent()){
            response = ResponseEntity.ok().body(productRepository.findById(id));
            productRepository.deleteById(id);
        }else {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

    public List<Product> getProductUser(String seller){
        return produccService.getProductsBySellerName(seller);
    }

    public void updateStockProduct(int stock, Long id){
        produccService.updateStock(stock, id);
    }
}
