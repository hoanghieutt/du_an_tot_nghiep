package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.entity.Color;
import com.poly.sd18.duantotnghiep.entity.Material;
import com.poly.sd18.duantotnghiep.model.ColorDTO;
import com.poly.sd18.duantotnghiep.model.MaterialDTO;
import com.poly.sd18.duantotnghiep.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("")
    public String getAllMaterial(@RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
                              Model model) {
        Page<Material> listMaterials = materialService.getAllMaterialPages(pageNo);
        model.addAttribute("listMaterial", listMaterials);
        model.addAttribute("totalPage", listMaterials.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "admin/material/index";
    }

    @PostMapping("/add")
    public ResponseEntity<?> createMaterial(@RequestBody MaterialDTO materialDTO) {
        Material material = materialService.createMaterial(materialDTO);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") Integer id,
                                        Model model) {
        Material material = materialService.findMaterialById(id);
        if (material != null) {
            model.addAttribute("material", material);
            return ResponseEntity.ok(material);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaterial(@RequestBody MaterialDTO materialDTO,
                                         @PathVariable("id") Integer id) {
        Material material = materialService.updateMaterial(materialDTO, id);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Material> lists = materialService.searchMaterialByName(name);
        model.addAttribute("listMaterial", lists);
        return "admin/material/index";
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Integer id) {
        Material material = materialService.setStatusMaterial(id);
        return ResponseEntity.ok(material);
    }
}
