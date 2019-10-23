package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class ReceiptEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String productName;
    private Double price;
    private int quantity;
    private Double tax;
    private double discount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Receipt receipt;
}
