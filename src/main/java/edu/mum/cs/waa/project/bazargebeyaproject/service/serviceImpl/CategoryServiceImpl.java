package edu.mum.cs.waa.project.bazargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.CategoryRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bazargebeyaproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> getAll() {
        return (List<Category>) categoryRepo.findAll() ;
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }
}
