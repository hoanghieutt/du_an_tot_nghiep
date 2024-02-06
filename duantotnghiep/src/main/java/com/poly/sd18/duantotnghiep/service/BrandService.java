package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();

    Brand add(Brand brand);

    Brand update(Brand brand, int id);

    Brand setStatus(int id);
}
