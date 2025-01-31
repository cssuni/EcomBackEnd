package com.Ecom.app.Ecom.Back.End.repository;

import com.Ecom.app.Ecom.Back.End.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
