package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.ProductOrder;

public interface ProductService {

    public Product save(Product p);

    public ProductOrder saveOrder(ProductOrder po);
}
