package com.pro.customize.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.customize.constant.Constant;
import com.pro.customize.databinding.ItemCartBinding;
import com.pro.customize.model.Food;
import com.pro.customize.utils.GlideUtils;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Food> mListFoods;
    private final IClickListener iClickListener;

    public interface IClickListener {
        void clickDeteteFood(Food food, int position);

        void updateItemFood(Food food, int position);
    }

    public CartAdapter(List<Food> mListFoods, IClickListener iClickListener) {
        this.mListFoods = mListFoods;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding itemCartBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(itemCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Food food = mListFoods.get(position);
        if (food == null) {
            return;
        }
        GlideUtils.loadUrl(food.getImage(), holder.mItemCartBinding.imgFoodCart);
        holder.mItemCartBinding.tvFoodNameCart.setText(food.getName());


        String strFoodPriceCart = food.getPrice() + Constant.CURRENCY;
        holder.mItemCartBinding.tvFoodPriceCart.setText(strFoodPriceCart);
        holder.mItemCartBinding.tvCount.setText(String.valueOf(food.getCount()));

        holder.mItemCartBinding.tvSubtract.setOnClickListener(v -> {
            String strCount = holder.mItemCartBinding.tvCount.getText().toString();
            int count = Integer.parseInt(strCount);
            if (count <= 1) {
                return;
            }
            int newCount = count - 1;
            holder.mItemCartBinding.tvCount.setText(String.valueOf(newCount));

            int totalPrice = food.getPrice() * newCount;
            food.setCount(newCount);
            food.setTotalPrice(totalPrice);

            iClickListener.updateItemFood(food, holder.getAdapterPosition());
        });

        holder.mItemCartBinding.tvAdd.setOnClickListener(v -> {
            int newCount = Integer.parseInt(holder.mItemCartBinding.tvCount.getText().toString()) + 1;
            holder.mItemCartBinding.tvCount.setText(String.valueOf(newCount));

            int totalPrice = food.getPrice() * newCount;
            food.setCount(newCount);
            food.setTotalPrice(totalPrice);

            iClickListener.updateItemFood(food, holder.getAdapterPosition());
        });

        holder.mItemCartBinding.tvDelete.setOnClickListener(v
                -> iClickListener.clickDeteteFood(food, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return null == mListFoods ? 0 : mListFoods.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        private final ItemCartBinding mItemCartBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.mItemCartBinding = itemCartBinding;
        }
    }
}
