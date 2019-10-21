package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;
    private String status;
    private LocalDate orderDate;
    //making the address field transient
//    @OneToOne
    @Embedded
    private Address shippingAddress;
    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private Receipt receipt;
}
