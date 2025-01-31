package com.Ecom.app.Ecom.Back.End.service.Cart;

import com.Ecom.app.Ecom.Back.End.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalAmount(Long cartId);

    Long initializeNewCart();
}
