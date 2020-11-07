package com.instacloud.order2fse.ui.AddSeller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSellerFragment extends Fragment {


    CardView sing_up;


    public AddSellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_seller, container, false);

        sing_up = view.findViewById(R.id.sing_up);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        BetterSpinner textView = (BetterSpinner)

                view.findViewById(R.id.IsExclusiveSpinner);

        textView.setAdapter(adapter);


        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    private static final String[] COUNTRIES = new String[]{

            "YES", "NO",

    };

}
