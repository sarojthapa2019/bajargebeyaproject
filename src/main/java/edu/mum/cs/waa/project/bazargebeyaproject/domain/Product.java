package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    private double unitPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    @Column(columnDefinition="VARCHAR(500)")
    private String description;

    private int stock;

    private Boolean isAnAdd;

    private String unit;
    private double tax;
    private double discount;

    @Transient
    private List<MultipartFile> pictures = new ArrayList<MultipartFile>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
