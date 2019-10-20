package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReceiptEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double price;
    private int quantity;
    private Double tax;
    private double discount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Receipt receipt;
}
