package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.model.Category;
import org.springframework.data.domain.Page;


public interface CategoryService {
    Page<Category> getAll(int pageNum);

    Page<Category> searchAll(int pageNum,String name);

    Page<Category> searchByStatus(int pageNum,String name, int status);

    Category findById(int id);

    Category add(Category category);

    Category update(Category category, int id);

    Category setStatus(int id);
}
