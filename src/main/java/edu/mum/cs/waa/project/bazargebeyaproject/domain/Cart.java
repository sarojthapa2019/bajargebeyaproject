package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "cart")
    private Buyer buyer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
