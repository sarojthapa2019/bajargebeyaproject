package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductOrderRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

}
