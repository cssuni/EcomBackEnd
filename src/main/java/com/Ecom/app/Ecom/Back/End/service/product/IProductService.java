package com.Ecom.app.Ecom.Back.End.service.product;

import com.Ecom.app.Ecom.Back.End.dto.ProductDto;
import com.Ecom.app.Ecom.Back.End.model.Product;
import com.Ecom.app.Ecom.Back.End.request.AddProductRequest;


import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
//
//    Product updateProduct(AddProductRequest request, Long Id);
//
//    Product updateProduct(AddProductRequest request, Long Id);

    Product updateProduct(AddProductRequest request, Long Id);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    List<ProductDto> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String category, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
