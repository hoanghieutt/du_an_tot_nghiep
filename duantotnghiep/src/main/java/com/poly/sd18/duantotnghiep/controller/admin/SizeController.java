package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.entity.Size;
import com.poly.sd18.duantotnghiep.model.SizeDTO;
import com.poly.sd18.duantotnghiep.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/sizes")
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("")
    public String getAllSize(@RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
                                 Model model) {
        Page<Size> listSizes = sizeService.getAllSizePages(pageNo);
        model.addAttribute("listSize", listSizes);
        model.addAttribute("totalPage", listSizes.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "admin/size/index";
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSize(@RequestBody SizeDTO sizeDTO) {
        Size size = sizeService.createSize(sizeDTO);
        return ResponseEntity.ok(size);
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") Integer id,
                                        Model model) {
        Size size = sizeService.findSizeById(id);
        if (size != null) {
            model.addAttribute("size", size);
            return ResponseEntity.ok(size);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSize(@RequestBody SizeDTO sizeDTO,
                                            @PathVariable("id") Integer id) {
        Size size = sizeService.updateSize(sizeDTO, id);
        return ResponseEntity.ok(size);
    }

    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Size> lists = sizeService.searchSizeByName(name);
        model.addAttribute("listSize", lists);
        return "admin/size/index";
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Integer id) {
        Size size = sizeService.setStatusSize(id);
        return ResponseEntity.ok(size);
    }
}
