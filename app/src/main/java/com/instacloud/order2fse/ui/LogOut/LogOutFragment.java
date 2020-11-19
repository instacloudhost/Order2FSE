package com.instacloud.order2fse.ui.LogOut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.instacloud.order2fse.ui.Login.LoginActivity;
import com.instacloud.order2fse.R;


public class LogOutFragment extends Fragment {

    private LogOutViewModel mViewModel;

    private SharedPreferences token;
    private String extremes = "extremeStorage";

    private String tokenid, userType;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        View root = inflater.inflate(R.layout.sign_in_fragment, container, false);
//        final TextView textView = root.findViewById(R.id.text_sign_in);
//        mViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        token = getActivity().getSharedPreferences(extremes,
                Context.MODE_PRIVATE);
        tokenid = token.getString("token", "");

        SharedPreferences.Editor editor = token.edit();
        editor.remove("token");
        editor.apply();
        Intent ma = new Intent(getContext(), LoginActivity.class);
        Toast.makeText(getActivity(),"Signed Out",Toast.LENGTH_SHORT).show();
        startActivity(ma);
        getActivity().finish();

        return root;
    }

}
