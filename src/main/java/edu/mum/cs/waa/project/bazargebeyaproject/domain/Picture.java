package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
