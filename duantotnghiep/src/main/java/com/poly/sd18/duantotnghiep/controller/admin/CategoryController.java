package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.model.Category;
import com.poly.sd18.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public String getAll(Model model) {
        List<Category> lists = categoryService.getAll();
        model.addAttribute("lists", lists);
        return "/admin/category/index";
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.add(category));
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") int id, Model model) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchAll")
    public String search(@RequestParam("name") String name, Model model) {
        List<Category> lists = categoryService.searchAll(name);
        model.addAttribute("lists",lists);
        return "/admin/category/index";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.update(category, id));
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.setStatus(id));
    }
}
