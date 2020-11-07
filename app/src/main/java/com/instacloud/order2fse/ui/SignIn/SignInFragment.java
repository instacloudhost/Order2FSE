package com.instacloud.order2fse.ui.SignIn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.instacloud.order2fse.R;


public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(SignInViewModel.class);
        View root = inflater.inflate(R.layout.sign_in_fragment, container, false);
//        final TextView textView = root.findViewById(R.id.text_sign_in);
//        mViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

}
