package com.instacloud.order2fse;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.instacloud.order2fse.ui.AddSeller.Fragment.AddSellerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtpVerificationFragment extends Fragment {

    Button otp_verification;


    public OtpVerificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otp_verification, container, false);


        otp_verification = view.findViewById(R.id.verify_otp);


        otp_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new AddSellerFragment());
                fr.commit();
            }
        });


        return view;
    }

}
