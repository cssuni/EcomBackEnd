package com.Ecom.app.Ecom.Back.End.controller;


import com.Ecom.app.Ecom.Back.End.model.Category;
import com.Ecom.app.Ecom.Back.End.responce.ApiResponse;
import com.Ecom.app.Ecom.Back.End.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
// whatever we have written in api.prefix ( in application.properties)  it will be added to this url
public class CategoriesController {

    private final CategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){

        try{
            List<Category> categories = categoryService.getAllCategories(); // calling service method
            return ResponseEntity.ok(new ApiResponse("Categories found successfully", categories));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse("Error in getting categories", null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){

        try{
            Category newCategory = categoryService.addCategory(category); // calling service method
            return ResponseEntity.ok(new ApiResponse("Category added successfully", newCategory));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category found successfully", category));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable String name){
        try{
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category found successfully", category));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateCategoryById( @RequestBody Category category, @PathVariable Long id) {
        try {
            categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category Updated successfully", category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

}
