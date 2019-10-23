package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToMany//(cascade = CascadeType.ALL)
//    private List<Product> products = new ArrayList<>();
    @ManyToOne//(cascade = CascadeType.ALL)
    @NotBlank
    private Buyer buyer;
    private String status;
    @CreationTimestamp
    private LocalDate orderDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
    //making the address field transientx
//    @OneToOne
    @Embedded
    @Valid
    private Address shippingAddress;
//    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private Receipt receipt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOrder")

    private List<CartEntry> cartEntries;

    public ProductOrder(){
        cartEntries = new ArrayList<>();
    }

    public void addOrderEntry(Cart cart){

    }
    @Override
    public String toString() {
        return status;
    }
}
