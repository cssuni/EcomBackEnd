package com.Ecom.app.Ecom.Back.End.repository;

import com.Ecom.app.Ecom.Back.End.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String brand);

    boolean existsByName(String name);

    Optional<Category> deleteByName(String name);

}

