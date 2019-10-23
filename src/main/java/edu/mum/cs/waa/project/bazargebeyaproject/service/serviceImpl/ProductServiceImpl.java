package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.ProductOrderRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.ProductRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductOrderRepo productOrderRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public ProductOrder saveOrder(ProductOrder po){
        return productOrderRepo.save(po);
    }

}
