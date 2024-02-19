package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.model.Category;
import com.poly.sd18.duantotnghiep.repository.CategoryRepository;
import com.poly.sd18.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> searchAll(int pageNum, String name) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return categoryRepository.searchAll(pageable, name);
    }

    @Override
    public Page<Category> searchByStatus(int pageNum, String name, int status) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return categoryRepository.searchByStatus(pageable, name, status);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
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
