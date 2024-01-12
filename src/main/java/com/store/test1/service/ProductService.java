package com.store.test1.service;

import com.store.test1.model.Product;
import com.store.test1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Método para guardar productos
    public void saveProducts(List<Product> products) {
        for (Product product : products) {
            productRepository.save(product);
        }
    }

    // Método para guardar un solo producto o actualizarlo si ya existe
    public void saveProduct(Product product) {
        // Verificar si el producto ya existe en la base de datos
        Optional<Product> existingProduct = productRepository.findById(product.getIdcodproducto());

        if (existingProduct.isPresent()) {
            // Si el producto ya existe, actualiza sus propiedades
            Product existing = existingProduct.get();
            existing.setDesc_prod(product.getDesc_prod());
            existing.setDesca_prod(product.getDesca_prod());
            existing.setPrecio_prod(product.getPrecio_prod());
            existing.setCodlistprecio_prod(product.getCodlistprecio_prod());
            existing.setNomlistprecio_prod(product.getNomlistprecio_prod());
            existing.setStock_prod(product.getStock_prod());

            // Puedes agregar lógica adicional según tus necesidades

            // Guarda el producto actualizado
            productRepository.save(existing);
        } else {
            // Si el producto no existe, simplemente guárdalo
            productRepository.save(product);
        }
    }

    // Método para obtener todos los productos con imágenes en formato Base64
    public List<Product> getAllProductsWithImages() {
        List<Product> products = productRepository.findAll();

        // Convertir las imágenes a Base64 antes de enviar la respuesta
        for (Product product : products) {
            product.setImageBase64(product.getImageBase64());
        }

        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Otros métodos según sea necesario
}
