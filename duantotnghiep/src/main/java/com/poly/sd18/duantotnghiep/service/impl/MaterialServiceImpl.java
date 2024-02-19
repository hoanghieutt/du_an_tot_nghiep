package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.entity.Material;
import com.poly.sd18.duantotnghiep.model.MaterialDTO;
import com.poly.sd18.duantotnghiep.repository.MaterialRepository;
import com.poly.sd18.duantotnghiep.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public List<Material> getAllMaterial() {
        return materialRepository.findAll();
    }

    @Override
    public Page<Material> getAllMaterialPages(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return materialRepository.findAll(pageable);
    }

    @Override
    public List<Material> searchMaterialByName(String name) {
        return materialRepository.searchMaterialByName(name);
    }

    @Override
    public List<Material> searchMaterialByStatus(Integer status) {
        return null;
    }

    @Override
    public Material createMaterial(MaterialDTO materialDTO) {
        Material material = Material.builder()
                .name(materialDTO.getName())
                .description(materialDTO.getDescription())
                .status(0)
                .build();
        return materialRepository.save(material);
    }

    @Override
    public Material findMaterialById(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    @Override
    public Material updateMaterial(MaterialDTO materialDTO, Integer id) {
        Material material = findMaterialById(id);
        if(material != null) {
            material.setName(materialDTO.getName());
            material.setDescription(materialDTO.getDescription());
            return materialRepository.save(material);
        }
        return null;
    }

    @Override
    public Material setStatusMaterial(Integer id) {
        Material searchMaterial = findMaterialById(id);
        if(searchMaterial != null) {
            if(searchMaterial.getStatus() == 1) {
                searchMaterial.setStatus(0);
            }else {
                searchMaterial.setStatus(1);
            }
            return materialRepository.save(searchMaterial);
        }
        return null;
    }
}
