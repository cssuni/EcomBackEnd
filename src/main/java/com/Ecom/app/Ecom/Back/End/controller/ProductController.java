package com.Ecom.app.Ecom.Back.End.controller;


import com.Ecom.app.Ecom.Back.End.dto.ProductDto;
import com.Ecom.app.Ecom.Back.End.exception.ProductNotFoundException;
import com.Ecom.app.Ecom.Back.End.model.Product;
import com.Ecom.app.Ecom.Back.End.request.AddProductRequest;
import com.Ecom.app.Ecom.Back.End.responce.ApiResponse;
import com.Ecom.app.Ecom.Back.End.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        return ResponseEntity.ok(
                new ApiResponse("Products found successfully", productService.getAllProducts()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        try{
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product found successfully",
                    productService.productToDto(product)));
        }catch (ProductNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct( @RequestBody AddProductRequest product) {
        try{
            Product newProduct = productService.addProduct(product); // calling service method
            return ResponseEntity.ok(new ApiResponse("Product added successfully",
                    productService.productToDto(newProduct)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct( @RequestBody AddProductRequest
                                                              product, @PathVariable Long id) {
        try {
            Product updatedProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully",
                    productService.productToDto(updatedProduct)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {

        List<ProductDto> products = productService.getProductsByBrandAndName(brand, name)
                .stream().map(productService::productToDto).toList();

        if(products.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Products not found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Products found successfully", products));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {

        List<ProductDto> products =  productService.getProductsByName(name)
                .stream().map(productService::productToDto).toList();;

        if(products.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Products not found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Products found successfully", products));
    }

    @GetMapping("/brand")
    public ResponseEntity<ApiResponse> getProducstByBrand(@RequestParam String brand) {

        List<ProductDto> products = productService.getProductsByBrand(brand).stream()
                .map(productService::productToDto).toList();;

        if(products.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Products not found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Products found successfully", products));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse> getAllProductsByCategory(@RequestParam String category){
        List<ProductDto> products = productService.getProductsByCategory(category)
                .stream().map(productService::productToDto).toList();;

        if(products.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("Products not found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Products found successfully", products));
    }

    @GetMapping("/count/by/brand-and-category")
    public ResponseEntity<ApiResponse> countProductsByBrandAndCategory(@RequestParam String brand, @RequestParam String category){

        return ResponseEntity.
                ok(new ApiResponse("Products count found successfully",
                        productService.countProductsByBrandAndName(brand, category)));

    }

    @GetMapping("/get/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String brand, @RequestParam String category){

        return ResponseEntity.
                ok(new ApiResponse("Products  found successfully",
                        productService.countProductsByBrandAndName(brand, category)));

    }

}
