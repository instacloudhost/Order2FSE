package com.instacloud.order2fse.ui.ShopByCategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.instacloud.order2fse.R;


public class ShopByCategoryFragment extends Fragment {

    private ShopByCategoryViewModel shopByCategoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopByCategoryViewModel =
                ViewModelProviders.of(this).get(ShopByCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop_by_category, container, false);
//        final TextView textView = root.findViewById(R.id.text_shop_by_category);
//        shopByCategoryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}