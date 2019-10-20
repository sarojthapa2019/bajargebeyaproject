package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Seller> followings;
    private String reward;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private List<ProductOrder> productOrders;

}
