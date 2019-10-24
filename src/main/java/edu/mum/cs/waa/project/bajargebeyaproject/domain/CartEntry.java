package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;
    private int quantity;
    @Nullable
    private String status;
    @ManyToOne
    private Cart cart;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductOrder productOrder;
    public CartEntry(){}
    public CartEntry(Cart cart){
        this.cart = cart;
    }

    //for subtotal of cartEntry

    public Double getSubTotal(){
        double subTotal = 0.0;
       subTotal = this.product.getUnitPrice() * this.quantity;
       return subTotal;
    }
}
