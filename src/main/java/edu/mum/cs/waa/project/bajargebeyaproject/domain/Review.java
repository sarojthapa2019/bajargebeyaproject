package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Buyer buyer;
    private Boolean isApproved;
    private Integer rating;
    @ManyToOne()
    private Product product;
}
