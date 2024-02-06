package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.repository.BrandRepository;
import com.poly.sd18.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand, int id) {
        Brand searchBrand = brandRepository.findById(id).get();
        if (searchBrand != null) {
            return brandRepository.save(brand);
        }
        return null;
    }

    @Override
    public Brand setStatus(int id) {
        Brand searchBrand = brandRepository.findById(id).get();
        if (searchBrand != null) {
            if (searchBrand.getStatus() == 1) {
                searchBrand.setStatus(0);
            } else {
                searchBrand.setStatus(1);
            }
            return brandRepository.save(searchBrand);
        }
        return null;
    }
}
