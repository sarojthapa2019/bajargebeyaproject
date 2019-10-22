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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CartEntry> cartEntries;

    public Cart(){
        cartEntries = new ArrayList<CartEntry>();
    }
    //for adding each cartEntry in cart
    public void addEntry(Product product){
        for(CartEntry cartEntry: cartEntries){
            System.out.println(cartEntry.getId());
            if(cartEntry.getProduct().getId()==product.getId()){
                cartEntry.setQuantity(cartEntry.getQuantity()+1);
                return;
            }
        }
        CartEntry cartEntry = new CartEntry(this);
        cartEntry.setProduct(product);
        cartEntry.setQuantity(1);
        this.getCartEntries().add(cartEntry);


    }

    public void updateProductQuantity(Product product, int quantity){
        for(CartEntry cartEntry: cartEntries){
            if(cartEntry.getProduct().getId()==product.getId()){
                cartEntry.setQuantity(cartEntry.getQuantity()+1);
            }
        }
    }
     public void removeEntry(Long id){
        CartEntry c = null;
        for(CartEntry cartEntry: cartEntries){
            if(cartEntry.getProduct().getId()==id){
                c = cartEntry;
                break;
            }
        }
        if(c != null){
            cartEntries.remove(c);
        }
    }

    //for clearing all the items from the Cart
    public void clearItems(){
        cartEntries = new ArrayList<CartEntry>();
    }

    //for counting the number of items
    public int getTotalItems(){
        int count  = 0;
        for(CartEntry cartEntry: cartEntries){
            count += cartEntry.getQuantity();
        }
        return count;
    }

    //calculating the total of cart products
    public Double getTotalAmount(){
        double total = 0.0;
        for(CartEntry cartEntry: cartEntries){
            total += cartEntry.getProduct().getUnitPrice() * cartEntry.getQuantity();
        }
        return total;
    }
}
