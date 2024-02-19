package com.poly.sd18.duantotnghiep.controller.admin;

import com.poly.sd18.duantotnghiep.dto.CategoryRequest;
import com.poly.sd18.duantotnghiep.model.Category;
import com.poly.sd18.duantotnghiep.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public String getAll(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                         Model model) {
        Page<Category> lists = categoryService.getAll(pageNum);
        model.addAttribute("lists", lists);
        model.addAttribute("totalCategories", lists.getTotalElements());
        model.addAttribute("totalPages", lists.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        return "/admin/category/index";
    }

    @PostMapping("/checkDuplicateName")
    public ResponseEntity<?> checkDuplicateName(@RequestBody CategoryRequest categoryRequest){
        List<Category> lists = categoryService.findByName(categoryRequest.getName());
        boolean isDuplicateName = false;
        if(lists.isEmpty()){
            isDuplicateName = true;
        }
        return ResponseEntity.ok(Map.of("isDuplicateName",isDuplicateName));
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
    public String search(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam("searchInput") String name,
                         @RequestParam("categoryStatus") int status,
                         Model model) {
        Page<Category> lists;
        if (status == -1) {
            lists = categoryService.searchAll(pageNum, name);
        } else {
            lists = categoryService.searchByStatus(pageNum, name, status);
        }
        model.addAttribute("lists", lists);
        model.addAttribute("totalCategories", lists.getTotalElements());
        model.addAttribute("totalPages", lists.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("searchInput", name);
        model.addAttribute("categoryStatus", status);
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
