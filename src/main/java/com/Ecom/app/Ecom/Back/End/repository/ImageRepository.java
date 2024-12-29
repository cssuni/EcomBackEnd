package com.Ecom.app.Ecom.Back.End.repository;

import com.Ecom.app.Ecom.Back.End.model.Image;
import com.Ecom.app.Ecom.Back.End.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByProduct(Product product);
}
