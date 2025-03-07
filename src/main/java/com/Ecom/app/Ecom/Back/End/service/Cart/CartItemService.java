package com.Ecom.app.Ecom.Back.End.service.Cart;

import com.Ecom.app.Ecom.Back.End.exception.ResourceNotFoundException;
import com.Ecom.app.Ecom.Back.End.model.Cart;
import com.Ecom.app.Ecom.Back.End.model.CartItem;
import com.Ecom.app.Ecom.Back.End.model.Product;
import com.Ecom.app.Ecom.Back.End.repository.CartItemRepository;
import com.Ecom.app.Ecom.Back.End.repository.CartRepository;
import com.Ecom.app.Ecom.Back.End.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartService cartService;


    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);

        CartItem cartItem = cart.getCartItems().stream() // Stream of cart items
                .filter(item -> item.getProduct().getId().equals(productId)) // Filter by product ID
                .findFirst() // Find the first cart item with the matching product ID
                .orElse(new CartItem()); // If not found, create a new cart item

        if(cartItem.getId() == null) {
            Product product = productService.getProductById(productId);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setProductTitle(product.getName());
            cartItemRepository.save(cartItem);
            cartItem.setCart(cart);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);

        }
        cartItem.setTotalPrice();
        cart.addCartItem(cartItem);
        cart.updateTotalAmount();
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(cartId, productId);

        cart.removeCartItem(cartItem);
        cart.updateTotalAmount();

        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(cartId, productId);

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice();
        cart.updateTotalAmount();

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems().stream() // Stream of cart items
                .filter(item -> item.getProduct().getId().equals(productId)) // Filter by product ID
                .findFirst() // Find the first cart item with the matching product ID
                .orElseThrow(()-> new ResourceNotFoundException("Product not found in cart")); // If not found, Throw Exception
    }
}
