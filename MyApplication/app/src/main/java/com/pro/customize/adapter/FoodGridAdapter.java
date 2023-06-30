package com.pro.customize.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.customize.constant.Constant;
import com.pro.customize.databinding.ItemFoodGridBinding;
import com.pro.customize.listener.IOnClickFoodItemListener;
import com.pro.customize.model.Food;
import com.pro.customize.utils.GlideUtils;

import java.util.List;

public class FoodGridAdapter extends RecyclerView.Adapter<FoodGridAdapter.FoodGridViewHolder> {

    private final List<Food> mListFoods;
    public final IOnClickFoodItemListener iOnClickFoodItemListener;

    public FoodGridAdapter(List<Food> mListFoods, IOnClickFoodItemListener iOnClickFoodItemListener) {
        this.mListFoods = mListFoods;
        this.iOnClickFoodItemListener = iOnClickFoodItemListener;
    }

    @NonNull
    @Override
    public FoodGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFoodGridBinding itemFoodGridBinding = ItemFoodGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FoodGridViewHolder(itemFoodGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodGridViewHolder holder, int position) {
        Food food = mListFoods.get(position);
        if (food == null) {
            return;
        }
        GlideUtils.loadUrl(food.getImage(), holder.mItemFoodGridBinding.imgFood);
        String strPrice = food.getPrice() + Constant.CURRENCY;
        holder.mItemFoodGridBinding.tvPriceSale.setText(strPrice);

        holder.mItemFoodGridBinding.tvFoodName.setText(food.getName());

        holder.mItemFoodGridBinding.layoutItem.setOnClickListener(v -> iOnClickFoodItemListener.onClickItemFood(food));
    }

    @Override
    public int getItemCount() {
        return null == mListFoods ? 0 : mListFoods.size();
    }

    public static class FoodGridViewHolder extends RecyclerView.ViewHolder {

        private final ItemFoodGridBinding mItemFoodGridBinding;

        public FoodGridViewHolder(ItemFoodGridBinding itemFoodGridBinding) {
            super(itemFoodGridBinding.getRoot());
            this.mItemFoodGridBinding = itemFoodGridBinding;
        }
    }
}
