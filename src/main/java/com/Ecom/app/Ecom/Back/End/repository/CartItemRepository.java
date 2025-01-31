package com.Ecom.app.Ecom.Back.End.repository;

import com.Ecom.app.Ecom.Back.End.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
