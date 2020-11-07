package com.instacloud.order2fse.ui.JioMartWallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.instacloud.order2fse.R;


public class JioMartWalletFragment extends Fragment {

    private JioMartWalletViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(JioMartWalletViewModel.class);
        View root = inflater.inflate(R.layout.fragment_jio_mart, container, false);
//        final TextView textView = root.findViewById(R.id.text_jio_mart);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}