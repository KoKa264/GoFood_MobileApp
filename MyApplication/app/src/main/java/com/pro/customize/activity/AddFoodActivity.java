package com.pro.customize.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pro.customize.ControllerApplication;
import com.pro.customize.R;
import com.pro.customize.constant.Constant;
import com.pro.customize.constant.GlobalFunction;
import com.pro.customize.databinding.ActivityAddFoodBinding;
import com.pro.customize.model.Food;
import com.pro.customize.model.FoodObject;
import com.pro.customize.model.Image;
import com.pro.customize.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddFoodActivity extends BaseActivity {

    private ActivityAddFoodBinding mActivityAddFoodBinding;
    private boolean isUpdate;
    private Food mFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddFoodBinding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(mActivityAddFoodBinding.getRoot());

        getDataIntent();
        initToolbar();
        initView();

        mActivityAddFoodBinding.btnAddOrEdit.setOnClickListener(v -> addOrEditFood());
    }

    private void getDataIntent() {
        Bundle bundleReceived = getIntent().getExtras();
        if (bundleReceived != null) {
            isUpdate = true;
            mFood = (Food) bundleReceived.get(Constant.KEY_INTENT_FOOD_OBJECT);
        }
    }

    private void initToolbar() {
        mActivityAddFoodBinding.toolbar.imgBack.setVisibility(View.VISIBLE);
        //mActivityAddFoodBinding.toolbar.imgCart.setVisibility(View.GONE);

        mActivityAddFoodBinding.toolbar.imgBack.setOnClickListener(v -> onBackPressed());
    }

    private void initView() {
        if (isUpdate) {
            mActivityAddFoodBinding.toolbar.tvTitle.setText(getString(R.string.edit_food));
            mActivityAddFoodBinding.btnAddOrEdit.setText(getString(R.string.action_edit));

            mActivityAddFoodBinding.edtName.setText(mFood.getName());
            mActivityAddFoodBinding.edtDescription.setText(mFood.getDescription());
            mActivityAddFoodBinding.edtPrice.setText(String.valueOf(mFood.getPrice()));
            mActivityAddFoodBinding.edtImage.setText(mFood.getImage());
        } else {
            mActivityAddFoodBinding.toolbar.tvTitle.setText(getString(R.string.add_food));
            mActivityAddFoodBinding.btnAddOrEdit.setText(getString(R.string.action_add));
        }
    }

    private void addOrEditFood() {
        String strName = mActivityAddFoodBinding.edtName.getText().toString().trim();
        String strDescription = mActivityAddFoodBinding.edtDescription.getText().toString().trim();
        String strPrice = mActivityAddFoodBinding.edtPrice.getText().toString().trim();
        String strImage = mActivityAddFoodBinding.edtImage.getText().toString().trim();

        if (StringUtil.isEmpty(strName)) {
            Toast.makeText(this, getString(R.string.msg_name_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strDescription)) {
            Toast.makeText(this, getString(R.string.msg_description_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strPrice)) {
            Toast.makeText(this, getString(R.string.msg_price_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strImage)) {
            Toast.makeText(this, getString(R.string.msg_image_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        // Update food
        if (isUpdate) {
            showProgressDialog(true);
            Map<String, Object> map = new HashMap<>();
            map.put("name", strName);
            map.put("description", strDescription);
            map.put("price", Integer.parseInt(strPrice));
            map.put("image", strImage);

            ControllerApplication.get(this).getFoodDatabaseReference()
                    .child(String.valueOf(mFood.getId())).updateChildren(map, (error, ref) -> {
                showProgressDialog(false);
                Toast.makeText(AddFoodActivity.this,
                        getString(R.string.msg_edit_food_success), Toast.LENGTH_SHORT).show();
                GlobalFunction.hideSoftKeyboard(this);
            });
            return;
        }

        // Add food
        showProgressDialog(true);
        long foodId = System.currentTimeMillis();
        FoodObject food = new FoodObject(foodId, strName, strDescription, Integer.parseInt(strPrice), strImage);
        ControllerApplication.get(this).getFoodDatabaseReference()
                .child(String.valueOf(foodId)).setValue(food, (error, ref) -> {
            showProgressDialog(false);
            mActivityAddFoodBinding.edtName.setText("");
            mActivityAddFoodBinding.edtDescription.setText("");
            mActivityAddFoodBinding.edtPrice.setText("");
            mActivityAddFoodBinding.edtImage.setText("");
            GlobalFunction.hideSoftKeyboard(this);
            Toast.makeText(this, getString(R.string.msg_add_food_success), Toast.LENGTH_SHORT).show();
        });
    }
}