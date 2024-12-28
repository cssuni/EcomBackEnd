package com.Ecom.app.Ecom.Back.End.service.category;

import com.Ecom.app.Ecom.Back.End.model.Category;


import java.util.List;

public interface ICategoryService {

    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategoryById(Long id);

//    List<Product> getAllProductsByCategoryName(String name);
//    List<Product> getAllProductsByCategoryName(Long id);


}
