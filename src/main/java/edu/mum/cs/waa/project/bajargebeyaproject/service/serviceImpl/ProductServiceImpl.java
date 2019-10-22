package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Image;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ProductOrder;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CategoryRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ImageRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductOrderRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ProductRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductOrderRepo productOrderRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ImageRepo imageRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public ProductOrder saveOrder(ProductOrder po){
        return productOrderRepo.save(po);
    }

    @Override
    public Category saveCategory(Category c) {
        return categoryRepo.save(c);
    }

    @Override
    public Category getCategoryById(Long i) {
        Optional<Category> cc = categoryRepo.findById(i);
        if(cc.isPresent())
            return cc.get();
        else
            return null;
    }

    @Override
    public Image saveImage(Image i) {
        return imageRepo.save(i);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

}
