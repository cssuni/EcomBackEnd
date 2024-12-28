package com.Ecom.app.Ecom.Back.End.service.category;


import com.Ecom.app.Ecom.Back.End.exception.CategoryAlreadyExistsException;
import com.Ecom.app.Ecom.Back.End.exception.ResourceNotFoundException;
import com.Ecom.app.Ecom.Back.End.model.Category;
import com.Ecom.app.Ecom.Back.End.model.Product;
import com.Ecom.app.Ecom.Back.End.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private CategoryRepository categoryRepository;


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category now found!!!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {

        return Optional.of(category)
                .filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new CategoryAlreadyExistsException("Category Already Exists!!!"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {

        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(category.getName());
                    return categoryRepository.save(existingCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Category now found!!!"));
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> { throw new ResourceNotFoundException("Category Not Found"); }
                );

    }


//    public List<Product> getAllProductsByCategoryName(String name) {
//        return List.of();
//    }
//
//    public List<Product> getAllProductsByCategoryName(Long id) {
//        return List.of();
//    }


}
