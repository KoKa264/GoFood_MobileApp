package com.pro.customize.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pro.customize.R;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }


    public void login(View view) {
        startActivity(new Intent(WelcomeActivity.this,SignInActivity.class));
    }

    public void register(View view) {

        startActivity(new Intent(WelcomeActivity.this,SignUpActivity.class));
    }
}