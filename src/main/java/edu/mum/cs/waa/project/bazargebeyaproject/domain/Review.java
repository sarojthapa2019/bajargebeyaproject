package edu.mum.cs.waa.project.bazargebeyaproject.domain;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;
    private boolean isApproved;
    private int rating;
    @ManyToOne()
    private Product product;
}
