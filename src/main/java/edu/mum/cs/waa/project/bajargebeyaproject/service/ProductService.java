package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;

import java.util.List;
import java.util.Optional;

import java.util.List;

public interface ProductService {

    public Product save(Product p);

    public ProductOrder saveOrder(ProductOrder po);

    public Category saveCategory(Category c);

    public Category getCategoryById(Long id);

    public Image saveImage(Image i);

    public List<Product> getProductByAds(boolean b);

    public List<Product> getProducts();
    public Optional<Product> findById(Long id);

    public List<ProductOrder> getAllProductOrderByBuyer(Buyer buyer);

    public void deleteProductOrder(ProductOrder productOrder);

    public ProductOrder findProductOrderById(Long id);
}
