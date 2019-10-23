package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany//(cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();
    private double unitPrice;
    @ManyToOne//(cascade = CascadeType.ALL)
    private User seller;
    private String description;
    private int stock;
    private boolean isAnAdd;
    private String unit;
    private double tax;
    private double discount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images = new ArrayList<>();
    @OneToMany(mappedBy = "product")//, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
