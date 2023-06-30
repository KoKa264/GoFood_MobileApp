package com.pro.customize.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.pro.customize.R;
import com.pro.customize.activity.ChangePasswordActivity;
import com.pro.customize.activity.MainActivity;
import com.pro.customize.activity.OrderHistoryActivity;
import com.pro.customize.activity.SignInActivity;
import com.pro.customize.constant.GlobalFunction;
import com.pro.customize.databinding.FragmentAccountBinding;
import com.pro.customize.prefs.DataStoreManager;

public class AccountFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAccountBinding fragmentAccountBinding = FragmentAccountBinding.inflate(inflater, container, false);

        fragmentAccountBinding.tvEmail.setText(DataStoreManager.getUser().getEmail());

        fragmentAccountBinding.layoutSignOut.setOnClickListener(v -> onClickSignOut());
        fragmentAccountBinding.layoutChangePassword.setOnClickListener(v -> onClickChangePassword());
        fragmentAccountBinding.layoutOrderHistory.setOnClickListener(v -> onClickOrderHistory());

        return fragmentAccountBinding.getRoot();
    }

    @Override
    protected void initToolbar() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setToolBar(false, getString(R.string.account));
        }
    }

    private void onClickOrderHistory() {
        GlobalFunction.startActivity(getActivity(), OrderHistoryActivity.class);
    }

    private void onClickChangePassword() {
        GlobalFunction.startActivity(getActivity(), ChangePasswordActivity.class);
    }

    private void onClickSignOut() {
        if (getActivity() == null) {
            return;
        }
        FirebaseAuth.getInstance().signOut();
        DataStoreManager.setUser(null);
        GlobalFunction.startActivity(getActivity(), SignInActivity.class);
        getActivity().finishAffinity();
    }
}
