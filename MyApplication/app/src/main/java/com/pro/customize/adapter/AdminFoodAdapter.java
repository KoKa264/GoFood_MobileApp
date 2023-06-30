package com.pro.customize.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.customize.constant.Constant;
import com.pro.customize.databinding.ItemAdminFoodBinding;
import com.pro.customize.listener.IOnManagerFoodListener;
import com.pro.customize.model.Food;
import com.pro.customize.utils.GlideUtils;

import java.util.List;

public class AdminFoodAdapter extends RecyclerView.Adapter<AdminFoodAdapter.AdminFoodViewHolder> {

    private final List<Food> mListFoods;
    public final IOnManagerFoodListener iOnManagerFoodListener;

    public AdminFoodAdapter(List<Food> mListFoods, IOnManagerFoodListener listener) {
        this.mListFoods = mListFoods;
        this.iOnManagerFoodListener = listener;
    }

    @NonNull
    @Override
    public AdminFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdminFoodBinding itemAdminFoodBinding = ItemAdminFoodBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminFoodViewHolder(itemAdminFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFoodViewHolder holder, int position) {
        Food food = mListFoods.get(position);
        if (food == null) {
            return;
        }
        GlideUtils.loadUrl(food.getImage(), holder.mItemAdminFoodBinding.imgFood);
        String strPrice = food.getPrice() + Constant.CURRENCY;
        holder.mItemAdminFoodBinding.tvPriceSale.setText(strPrice);
        holder.mItemAdminFoodBinding.tvFoodName.setText(food.getName());
        holder.mItemAdminFoodBinding.tvFoodDescription.setText(food.getDescription());

        holder.mItemAdminFoodBinding.imgEdit.setOnClickListener(v -> iOnManagerFoodListener.onClickUpdateFood(food));
        holder.mItemAdminFoodBinding.imgDelete.setOnClickListener(v -> iOnManagerFoodListener.onClickDeleteFood(food));
    }

    @Override
    public int getItemCount() {
        return null == mListFoods ? 0 : mListFoods.size();
    }

    public static class AdminFoodViewHolder extends RecyclerView.ViewHolder {

        private final ItemAdminFoodBinding mItemAdminFoodBinding;

        public AdminFoodViewHolder(ItemAdminFoodBinding itemAdminFoodBinding) {
            super(itemAdminFoodBinding.getRoot());
            this.mItemAdminFoodBinding = itemAdminFoodBinding;
        }
    }
}
