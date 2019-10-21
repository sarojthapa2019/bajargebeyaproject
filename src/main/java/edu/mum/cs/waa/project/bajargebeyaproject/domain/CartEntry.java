package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne//(cascade = CascadeType.ALL)
    private Product product;
    private int quantity;
    @ManyToOne
    private Cart cart;
}
