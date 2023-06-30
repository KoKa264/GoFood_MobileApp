package com.pro.customize.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.customize.R;
import com.pro.customize.constant.Constant;
import com.pro.customize.databinding.ItemOrderBinding;
import com.pro.customize.model.Order;
import com.pro.customize.utils.DateTimeUtils;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private final List<Order> mListOrder;

    public OrderAdapter(Context mContext, List<Order> mListOrder) {
        this.mContext = mContext;
        this.mListOrder = mListOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding itemOrderBinding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new OrderViewHolder(itemOrderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if (order == null) {
            return;
        }
        if (order.isCompleted()) {
            holder.mItemOrderBinding.layoutItem.setBackgroundColor(mContext.getResources().getColor(R.color.black_overlay));
        } else {
            holder.mItemOrderBinding.layoutItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.mItemOrderBinding.tvId.setText(String.valueOf(order.getId()));
        holder.mItemOrderBinding.tvName.setText(order.getName());
        holder.mItemOrderBinding.tvPhone.setText(order.getPhone());
        holder.mItemOrderBinding.tvAddress.setText(order.getAddress());
        holder.mItemOrderBinding.tvMenu.setText(order.getFoods());
        holder.mItemOrderBinding.tvDate.setText(DateTimeUtils.convertTimeStampToDate(order.getId()));

        String strAmount = order.getAmount() + Constant.CURRENCY;
        holder.mItemOrderBinding.tvTotalAmount.setText(strAmount);
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    public void release() {
        mContext = null;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderBinding mItemOrderBinding;

        public OrderViewHolder(@NonNull ItemOrderBinding itemOrderBinding) {
            super(itemOrderBinding.getRoot());
            this.mItemOrderBinding = itemOrderBinding;
        }
    }
}
