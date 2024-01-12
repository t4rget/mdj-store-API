package com.store.test1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcodcategory;

    private String name_cat;
    private int status_cat;

}
