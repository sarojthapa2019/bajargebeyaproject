package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private List<Category> categories;
    private double unitPrice;
    private Seller seller;
    private String description;
    private int stock;
    private boolean isAnAdd;
    private String unit;
    private List<Image> images;
    private List<Review> reviews;

}
