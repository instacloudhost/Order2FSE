package com.instacloud.order2fse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OtpVerificationActivity extends AppCompatActivity {

    CardView otp_verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otp_verification = findViewById(R.id.otp_verification);

        otp_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent otpIntent = new Intent(OtpVerificationActivity.this,VerificationCodeActivity.class);
                startActivity(otpIntent);
            }
        });


    }
}