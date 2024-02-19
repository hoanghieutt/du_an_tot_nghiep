package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.entity.Size;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import com.poly.sd18.duantotnghiep.model.SizeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SizeService {
    List<Size> getAllSize();
    Page<Size> getAllSizePages(Integer pageNo);
    List<Size> searchSizeByName(String name);
    List<Size> searchSizeByStatus(Integer status);
    Size createSize(SizeDTO sizeDTO);
    Size findSizeById(Integer id);
    Size updateSize(SizeDTO sizeDTO, Integer id);
    Size setStatusSize(Integer id);
}
