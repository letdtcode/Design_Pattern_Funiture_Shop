package com.it.design_pattern_furniture_web.models.view_models.wish_items;

public class WishItemUpdateRequest {
    private int wishListItemId;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getWishListItemId() {
        return wishListItemId;
    }

    public void setWishListItemId(int wishListItemId) {
        this.wishListItemId = wishListItemId;
    }
}
