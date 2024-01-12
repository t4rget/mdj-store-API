package com.store.test1.controller;

import com.store.test1.model.Category;
import com.store.test1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody List<Category> categorys) {
        try {
            // Itera sobre la lista de categorias y guarda cada uno individualmente
            for (Category category : categorys) {
                categoryRepository.save(category);
            }

            return new ResponseEntity<>("Productos guardados exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar los productos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método GET para obtener todas las categorias
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categorys = categoryRepository.findAll();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }
}