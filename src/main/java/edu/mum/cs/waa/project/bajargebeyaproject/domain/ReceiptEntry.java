package edu.mum.cs.waa.project.bajargebeyaproject.domain;

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
    private Integer quantity;
    private Double tax;
    private Double discount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Receipt receipt;
}
