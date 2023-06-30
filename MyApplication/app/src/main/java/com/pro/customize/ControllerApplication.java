package com.pro.customize;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.customize.constant.Constant;
import com.pro.customize.prefs.DataStoreManager;

public class ControllerApplication extends Application {

    private FirebaseDatabase mFirebaseDatabase;

    public static ControllerApplication get(Context context) {
        return (ControllerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance(Constant.FIREBASE_URL);
        DataStoreManager.init(getApplicationContext());
    }

    public DatabaseReference getFoodDatabaseReference() {
        return mFirebaseDatabase.getReference("/food");
    }

    public DatabaseReference getBookingDatabaseReference() {
        return mFirebaseDatabase.getReference("/order");
    }
}