package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.model.Category;
import com.poly.sd18.duantotnghiep.repository.BrandRepository;
import com.poly.sd18.duantotnghiep.repository.CategoryRepository;
import com.poly.sd18.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchAll(String name) {
        return categoryRepository.searchAll(name);
    }

    @Override
    public List<Category> searchByStatus(String name, int status) {
        return categoryRepository.searchByStatus(name, status);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, int id) {
        Category searchCategory = categoryRepository.findById(id).get();
        if (searchCategory != null) {
            searchCategory.setName(category.getName());
            searchCategory.setDescription(category.getDescription());
            searchCategory.setUpdatedDate(category.getUpdatedDate());
            return categoryRepository.save(searchCategory);
        }
        return null;
    }

    @Override
    public Category setStatus(int id) {
        Category searchCategory = categoryRepository.findById(id).get();
        if (searchCategory != null) {
            if (searchCategory.getStatus() == 1) {
                searchCategory.setStatus(0);
            } else {
                searchCategory.setStatus(1);
            }
            return categoryRepository.save(searchCategory);
        }
        return null;
    }
}
