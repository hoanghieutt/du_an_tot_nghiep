package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.dto.BrandRequest;
import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping()
    public String getAll(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                         Model model) {
        Page<Brand> lists = brandService.getAll(pageNum);
        model.addAttribute("lists", lists);
        model.addAttribute("totalCategories", lists.getTotalElements());
        model.addAttribute("totalPages", lists.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        return "/admin/brand/index";
    }

    @PostMapping("/checkDuplicateName")
    public ResponseEntity<?> checkDuplicateName(@RequestBody BrandRequest brandRequest){
        List<Brand> lists = brandService.findByName(brandRequest.getName());
        boolean isDuplicateName = false;
        if(lists.isEmpty()){
            isDuplicateName = true;
        }
        return ResponseEntity.ok(Map.of("isDuplicateName",isDuplicateName));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.add(brand));
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") int id, Model model) {
        Brand brand = brandService.findById(id);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return ResponseEntity.ok(brand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchAll")
    public String search(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam("searchInput") String name,
                         @RequestParam("brandStatus") int status,
                         Model model) {
        Page<Brand> lists;
        if (status == -1) {
            lists = brandService.searchAll(pageNum, name);
        } else {
            lists = brandService.searchByStatus(pageNum, name, status);
        }
        model.addAttribute("lists", lists);
        model.addAttribute("totalBrands", lists.getTotalElements());
        model.addAttribute("totalPages", lists.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("searchInput", name);
        model.addAttribute("brandStatus", status);
        return "/admin/brand/index";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.update(brand, id));
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.setStatus(id));
    }
}
