package com.Ecom.app.Ecom.Back.End.service.Cart;

import com.Ecom.app.Ecom.Back.End.model.CartItem;

public interface ICartItemService {

     void addItemToCart(Long cartId, Long productId, int quantity);

     void removeItemFromCart(Long cartId, Long productId);

     void updateItemQuantity(Long cartId, Long productId, int quantity);

     CartItem getCartItem(Long cartId, Long productId);
}
