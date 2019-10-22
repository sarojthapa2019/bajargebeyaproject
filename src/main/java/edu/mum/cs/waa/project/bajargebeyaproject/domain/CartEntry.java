package edu.mum.cs.waa.project.bajargebeyaproject.domain;

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
    @ManyToOne
    private Cart cart;

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
