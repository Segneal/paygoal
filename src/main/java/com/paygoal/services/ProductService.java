package com.paygoal.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paygoal.models.Product;
import com.paygoal.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);

        
        if (!existingProduct.isPresent()) {
            throw new NoSuchElementException("Product not found");
        }
        Product productUpdate = existingProduct.get();
        if (product.getName() != null) {
            productUpdate.setName(product.getName());
        }
        if (product.getDescription() != null) {
            productUpdate.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            productUpdate.setPrice(product.getPrice());
        }
        return productRepository.save(productUpdate);

        
        
    }

    public List<Product> getAllOrderedByPrice(String order) {
        //returns products ordered by price ascending

        if (order.equals("desc")) {
            return productRepository.findAllByOrderByPriceDesc();
        } 

        return productRepository.findAllByOrderByPriceAsc();
    }


    public void deleteProduct(long id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            throw new NoSuchElementException("Product not found");
        }
        productRepository.delete(existingProduct.get());
    }

}


