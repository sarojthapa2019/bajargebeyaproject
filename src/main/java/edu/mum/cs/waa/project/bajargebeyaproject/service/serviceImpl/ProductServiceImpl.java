package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ProductOrder;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductOrderRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
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
