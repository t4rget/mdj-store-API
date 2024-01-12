package com.store.test1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;

@Data
@Entity
public class Product {

    @Id
    private Long idcodproducto;

    private String desc_prod;
    private String desca_prod;
    private double precio_prod;
    private int codlistprecio_prod;
    private String nomlistprecio_prod;
    private int stock_prod;
    private String urlimagen_prod;
    private  String imageBase64;

    @Lob
    private byte[] image;

    // Método para convertir la imagen a Base64

    public String getImageBase64() {
        if (image != null && image.length > 0) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }

}
