package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Image;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ProductOrder;

import java.util.Optional;

public interface ProductService {

    public Product save(Product p);

    public ProductOrder saveOrder(ProductOrder po);

    public Category saveCategory(Category c);

    public Category getCategoryById(Long id);

    public Image saveImage(Image i);

    public Optional<Product> findById(Long id);
}
