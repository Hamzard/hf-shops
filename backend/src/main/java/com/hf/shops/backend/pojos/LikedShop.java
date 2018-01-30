package com.hf.shops.backend.pojos;

import com.hf.shops.backend.entities.Shop;

public class LikedShop implements Comparable{

    private Shop id;
    private float value;

    public LikedShop() {}

    public LikedShop(Shop id, float value) {
        this.id = id;
        this.value = value;
    }

    public Shop getId() {
        return id;
    }

    public void setId(Shop id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return this.value < ((LikedShop) o).getValue() ? -1 : 1;
    }
}
