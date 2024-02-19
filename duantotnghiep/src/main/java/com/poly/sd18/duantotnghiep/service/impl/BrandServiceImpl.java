package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.model.Category;
import com.poly.sd18.duantotnghiep.repository.BrandRepository;
import com.poly.sd18.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;


    @Override
    public Page<Brand> getAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return brandRepository.findAll(pageable);
    }

    @Override
    public Page<Brand> searchAll(int pageNum, String name) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return brandRepository.searchAll(pageable,name);
    }

    @Override
    public Page<Brand> searchByStatus(int pageNum, String name, int status) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5);
        return brandRepository.searchByStatus(pageable, name, status);
    }

    @Override
    public Brand findById(int id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public List<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand, int id) {
        Brand searchBrand = brandRepository.findById(id).get();
        if (searchBrand != null) {
            searchBrand.setName(brand.getName());
            searchBrand.setDescription(brand.getDescription());
            searchBrand.setUpdatedDate(brand.getUpdatedDate());
            return brandRepository.save(searchBrand);
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
