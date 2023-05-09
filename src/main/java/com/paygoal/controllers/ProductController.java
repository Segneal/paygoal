package com.paygoal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paygoal.models.Product;
import com.paygoal.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Product>> getAllProducts() {

        //default products for testing purposes
        Product p = new Product( "Product 1", "Product 1 description", 100.00);
        Product p1 = new Product( "Product 2", "Product 2 description", 40.00);
        Product p2 = new Product( "Product 3", "Product 3 description", 150.00);
        Product p3 = new Product( "Product 4", "Product 4 description", 10.00);
        List<Product> products = new ArrayList<Product>();
        products.add(p);
        products.add(p1);
        products.add(p2);
        products.add(p3);

        products.forEach(product  -> productService.createProduct(product ));
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        System.out.println(id);
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } 
        return ResponseEntity.notFound().build();
    }    
    
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAll/asc")  
    public ResponseEntity<Iterable<Product>> getAllProductsAsc() {
        String order = "asc";
        return ResponseEntity.ok(productService.getAllOrderedByPrice(order));
    }

    @GetMapping("/getAll/desc")
    public ResponseEntity<Iterable<Product>> getAllProductsDesc() {
        String order = "desc";
        return ResponseEntity.ok(productService.getAllOrderedByPrice(order));
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) { 
            return ResponseEntity.notFound().build(); 
        }
    }

}
