package com.poly.sd18.duantotnghiep.service.impl;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import com.poly.sd18.duantotnghiep.repository.ColorRepository;
import com.poly.sd18.duantotnghiep.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public Page<Color> getAllColorPages(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return colorRepository.findAll(pageable);
    }

    @Override
    public List<Color> searchColorByName(String name) {
        return colorRepository.searchColorByName(name);
    }

    @Override
    public List<Color> searchColorByStatus(Integer status) {
        return null;
    }

    @Override
    public Color createColor(ColorDTO colorDTO) {
        Color color = Color.builder()
                .name(colorDTO.getName())
                .description(colorDTO.getDescription())
                .status(0)
                .build();
        return colorRepository.save(color);
    }

    @Override
    public Color findColorById(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Color updateColor(ColorDTO colorDTO, Integer id) {
        Color color = findColorById(id);
        if(color != null) {
            color.setName(colorDTO.getName());
            color.setDescription(colorDTO.getDescription());
            return colorRepository.save(color);
        }
        return null;
    }

    @Override
    public Color setStatusColor(Integer id) {
        Color searchColor = findColorById(id);
        if(searchColor != null) {
            if(searchColor.getStatus() == 1) {
                searchColor.setStatus(0);
            }else {
                searchColor.setStatus(1);
            }
            return colorRepository.save(searchColor);
        }
        return null;
    }
}
