package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import com.poly.sd18.duantotnghiep.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/colors")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

//    @GetMapping("/findAll")
//    public String getAllColor(Model model) {
//        List<Color> colors = colorService.getAllColor();
//        model.addAttribute("listColor", colors);
//        return "admin/color/index";
//    }

    @GetMapping("")
    public String getAllColor(@RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
                              Model model) {
        Page<Color> listColors = colorService.getAllColorPages(pageNo);
        model.addAttribute("listColor", listColors);
        model.addAttribute("totalPage", listColors.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "admin/color/index";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addColor(@RequestBody ColorDTO colorDTO) {
        Color color = colorService.createColor(colorDTO);
        return ResponseEntity.ok(color);
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") Integer id,
                                        Model model) {
        Color color = colorService.findColorById(id);
        if (color != null) {
            model.addAttribute("color", color);
            return ResponseEntity.ok(color);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateColor(@RequestBody ColorDTO colorDTO,
                                         @PathVariable("id") Integer id) {
        Color color = colorService.updateColor(colorDTO, id);
        return ResponseEntity.ok(color);
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Color> lists = colorService.searchColorByName(name);
        model.addAttribute("listColor", lists);
        return "admin/color/index";
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Integer id) {
        Color color = colorService.setStatusColor(id);
        return ResponseEntity.ok(color);
    }
}
