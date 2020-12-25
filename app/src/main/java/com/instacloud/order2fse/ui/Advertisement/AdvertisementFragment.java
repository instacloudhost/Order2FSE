package com.instacloud.order2fse.ui.Advertisement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.instacloud.order2fse.R;

public class AdvertisementFragment extends Fragment {


    Button crete_add_btn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advertisement_fragment, container, false);



        crete_add_btn = (Button) view.findViewById(R.id.crete_add_btn);
//        crete_add_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getContext(),CreateAdActivity.class);
//                startActivity(intent);
//
//            }
//        });

        return view;
    }


}