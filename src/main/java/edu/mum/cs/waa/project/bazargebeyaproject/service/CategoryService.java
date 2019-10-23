package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category save(Category category);

    void delete(Long id);
}
