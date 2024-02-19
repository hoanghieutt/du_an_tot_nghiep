package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ColorService {
    List<Color> getAllColor();
    Page<Color> getAllColorPages(Integer pageNo);
    List<Color> searchColorByName(String name);
    List<Color> searchColorByStatus(Integer status);
    Color createColor(ColorDTO colorDTO);
    Color findColorById(Integer id);
    Color updateColor(ColorDTO colorDTO, Integer id);
    Color setStatusColor(Integer id);
}
