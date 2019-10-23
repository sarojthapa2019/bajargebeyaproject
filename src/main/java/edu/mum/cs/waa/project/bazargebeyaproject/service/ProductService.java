package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.ProductOrder;

import java.util.List;

public interface ProductService {

    Product save(Product p);

    ProductOrder saveOrder(ProductOrder po);

    List<Product> getAll();

    void delete(Long id);

    Product findById(Long id);
}
