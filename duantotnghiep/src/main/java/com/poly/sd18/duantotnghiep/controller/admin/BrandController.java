package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.model.Brand;
import com.poly.sd18.duantotnghiep.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.add(brand));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.update(brand, id));
    }

    @PutMapping("/setStatus/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.setStatus(id));
    }
}
