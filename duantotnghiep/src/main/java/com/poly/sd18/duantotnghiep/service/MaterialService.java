package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.entity.Material;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import com.poly.sd18.duantotnghiep.model.MaterialDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterial();
    Page<Material> getAllMaterialPages(Integer pageNo);
    List<Material> searchMaterialByName(String name);
    List<Material> searchMaterialByStatus(Integer status);
    Material createMaterial(MaterialDTO materialDTO);
    Material findMaterialById(Integer id);
    Material updateMaterial(MaterialDTO materialDTO, Integer id);
    Material setStatusMaterial(Integer id);
}
