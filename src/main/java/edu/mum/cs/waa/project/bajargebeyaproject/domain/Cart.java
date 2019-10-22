package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToOne(mappedBy = "cart")
//    private Buyer buyer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")//, fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CartEntry> cartEntries = new ArrayList<>();

    //calculating the total of cart products
//    @Transient
//    private Double totalAmount(){
//        double total = 0.0;
//        for(Product product: products){
//            total += quantity.get(product) * product.getUnitPrice();
//        }
//        return total;
//    }
}
