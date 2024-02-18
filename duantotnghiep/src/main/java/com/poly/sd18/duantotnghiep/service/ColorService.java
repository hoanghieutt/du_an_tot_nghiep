package com.poly.sd18.duantotnghiep.service;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.model.ColorDTO;

import java.util.List;

public interface ColorService {
    List<Color> getAllColor();
    List<Color> searchColorByName(String name);
    List<Color> searchColorByStatus(Integer status);
    Color createColor(ColorDTO colorDTO);
    Color findColorById(Integer id);
    Color updateColor(ColorDTO colorDTO, Integer id);
    Color setStatusColor(Integer id);
}
