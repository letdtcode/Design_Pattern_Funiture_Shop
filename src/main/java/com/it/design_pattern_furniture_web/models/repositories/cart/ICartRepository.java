package com.it.design_pattern_furniture_web.models.repositories.cart;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ICartRepository extends IModifyEntity<CartItemCreateRequest, CartItemUpdateRequest, Integer>,
        IRetrieveEntity<CartItemViewModel, CartItemGetPagingRequest, Integer> {
    ArrayList<CartItemViewModel> retrieveCartByUserId(int userId);

    int getCartIdByUserId(int userId);

    CartItemViewModel getCartItemContain(int cartId, int productId);

    boolean deleteCartByUserId(int userId);

    int canUpdateQuantity(int cartItemId, int quantity);

    void updateQuantityByProductId(int productId, int quantity);

    String addProductToCart(int productId, int quantity, int userId);

    BigDecimal getTotalCartItemPriceByUserId(int userId);
}
