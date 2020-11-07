package com.instacloud.order2fse.ui.MobileVerificationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.instacloud.order2fse.OtpVerificationFragment;
import com.instacloud.order2fse.R;


public class MobileVerificationFragment extends Fragment {

    private MobileVerificationViewModel mViewModel;

    CardView otp_verification;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(MobileVerificationViewModel.class);
        View root = inflater.inflate(R.layout.mobile_verification_fragment, container, false);

        otp_verification = root.findViewById(R.id.otp_verification);

        otp_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,new OtpVerificationFragment());
                fr.commit();

            }
        });


//        final TextView textView = root.findViewById(R.id.text_all_offers);
//        mViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
