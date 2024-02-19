package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.model.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    Page<Brand> getAll(int pageNum);

    Page<Brand> searchAll(int pageNum,String name);

    Page<Brand> searchByStatus(int pageNum,String name, int status);

    Brand findById(int id);

    List<Brand> findByName(String name);

    Brand add(Brand brand);

    Brand update(Brand brand, int id);

    Brand setStatus(int id);
}
