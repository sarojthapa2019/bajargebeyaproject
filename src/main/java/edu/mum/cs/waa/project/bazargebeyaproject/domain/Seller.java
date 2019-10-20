package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private List<Product> products;
    private boolean isApproved;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "followings")
    private List<Buyer> followers;
}
