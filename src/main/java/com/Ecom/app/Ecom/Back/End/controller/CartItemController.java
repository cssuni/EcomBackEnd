package com.Ecom.app.Ecom.Back.End.controller;

import com.Ecom.app.Ecom.Back.End.exception.CartNotFooundException;
import com.Ecom.app.Ecom.Back.End.exception.ResourceNotFoundException;
import com.Ecom.app.Ecom.Back.End.responce.ApiResponse;
import com.Ecom.app.Ecom.Back.End.service.Cart.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam int quantity) {
        try {
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart", null));
        } catch (CartNotFooundException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam Long cartId,
                                                          @RequestParam Long productId){
        cartItemService.removeItemFromCart(cartId, productId);
        return ResponseEntity.ok(new ApiResponse("Item removed from cart", null));
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<ApiResponse> updateQuantity(@RequestParam Long cartId,
                                                      @RequestParam Long productId,
                                                      @RequestParam int quantity){
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item quantity updated", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }



}
