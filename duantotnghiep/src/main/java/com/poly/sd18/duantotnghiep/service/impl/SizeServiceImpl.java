package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.entity.Material;
import com.poly.sd18.duantotnghiep.entity.Size;
import com.poly.sd18.duantotnghiep.model.SizeDTO;
import com.poly.sd18.duantotnghiep.repository.SizeRepository;
import com.poly.sd18.duantotnghiep.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAll();
    }

    @Override
    public Page<Size> getAllSizePages(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> searchSizeByName(String name) {
        return sizeRepository.searchSizeByName(name);
    }

    @Override
    public List<Size> searchSizeByStatus(Integer status) {
        return null;
    }

    @Override
    public Size createSize(SizeDTO sizeDTO) {
        Size size = Size.builder()
                .name(sizeDTO.getName())
                .shirtLength(sizeDTO.getShirtLength())
                .shirtWidth(sizeDTO.getShirtWidth())
                .status(0)
                .build();
        return sizeRepository.save(size);
    }

    @Override
    public Size findSizeById(Integer id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public Size updateSize(SizeDTO sizeDTO, Integer id) {
        Size size = findSizeById(id);
        if(size != null) {
            size.setName(sizeDTO.getName());
            size.setShirtLength(sizeDTO.getShirtLength());
            size.setShirtWidth(sizeDTO.getShirtWidth());
            return sizeRepository.save(size);
        }
        return null;
    }

    @Override
    public Size setStatusSize(Integer id) {
        Size searchSize = findSizeById(id);
        if(searchSize != null) {
            if(searchSize.getStatus() == 1) {
                searchSize.setStatus(0);
            }else {
                searchSize.setStatus(1);
            }
            return sizeRepository.save(searchSize);
        }
        return null;
    }
}
