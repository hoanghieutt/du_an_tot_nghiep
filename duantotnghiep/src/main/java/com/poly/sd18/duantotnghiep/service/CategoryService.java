package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    List<Category> searchAll(String name);

    List<Category> searchByStatus(String name, int status);

    Category findById(int id);

    Category add(Category category);

    Category update(Category category, int id);

    Category setStatus(int id);
}
