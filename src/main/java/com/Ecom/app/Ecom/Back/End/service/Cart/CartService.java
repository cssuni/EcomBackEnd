package com.Ecom.app.Ecom.Back.End.service.Cart;

import com.Ecom.app.Ecom.Back.End.exception.CartNotFooundException;
import com.Ecom.app.Ecom.Back.End.model.Cart;
import com.Ecom.app.Ecom.Back.End.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFooundException("Cart not found"));
    }

    @Override
    public void clearCart(Long cartId) {

        cartRepository.deleteById(cartId);

    }

    @Override
    public BigDecimal getTotalAmount(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFooundException("Cart not found"));

        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        newCart.setTotalAmount(BigDecimal.ZERO);
        return cartRepository.save(newCart).getId();

    }
}
