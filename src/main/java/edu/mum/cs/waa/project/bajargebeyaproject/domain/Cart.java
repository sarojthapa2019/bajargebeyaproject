package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

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
    @OneToOne(mappedBy = "cart")
    private Buyer buyer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartEntry> cartEntries = new ArrayList<>();

    public Cart(){
        cartEntries = new ArrayList<CartEntry>();
    }
    //for adding each cartEntry in cart
    public void addEntry(Product product){
        for(CartEntry cartEntry: cartEntries){
            if(cartEntry.getProduct().getId()==product.getId()){
                cartEntry.setQuantity(cartEntry.getQuantity()+1);
                return;
            }
        }
        CartEntry cartEntry = new CartEntry();
        cartEntry.setProduct(product);
        cartEntry.setQuantity(1);
        this.cartEntries.add(cartEntry);
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
