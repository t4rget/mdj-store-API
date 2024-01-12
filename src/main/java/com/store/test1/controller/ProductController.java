package com.store.test1.controller;

import com.store.test1.model.Product;
import com.store.test1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/save")
    public ResponseEntity<String> saveProducts(@RequestBody List<Product> products) {
        try {
            productService.saveProducts(products);
            return new ResponseEntity<>("Productos guardados exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar los productos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProductsWithImages() {
        List<Product> products = productService.getAllProductsWithImages();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);

        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Nuevo endpoint para guardar o actualizar un producto por su ID
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/{id}")
    public ResponseEntity<String> saveOrUpdateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            product.setIdcodproducto(id);
            productService.saveProduct(product);
            return new ResponseEntity<>("Producto guardado o actualizado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar o actualizar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

