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
    private Double unitPrice=0.0;
    @ManyToOne//(cascade = CascadeType.ALL)
    private User seller;
    private String description;
    private Integer stock;
    private Boolean isAnAdd=false;
    private String unit;
    private Double tax=0.0;
    private Double discount=0.0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images = new ArrayList<>();
    @OneToMany(mappedBy = "product")//, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
