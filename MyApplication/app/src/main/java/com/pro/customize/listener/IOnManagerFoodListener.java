package com.pro.customize.listener;

import com.pro.customize.model.Food;

public interface IOnManagerFoodListener {
    void onClickUpdateFood(Food food);
    void onClickDeleteFood(Food food);
}
