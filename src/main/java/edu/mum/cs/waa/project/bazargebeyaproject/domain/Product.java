package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categories;
    private double unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    private Seller seller;
    private String description;
    private int stock;
    private boolean isAnAdd;
    private String unit;
    private double tax;
    private double discount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> images;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
