package com.instacloud.order2fse.ui.ShopDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.UploadDocuments.UploadDocumentsActivity;

public class PaymentGatewayActivity extends AppCompatActivity {

    EditText gst_number,pan_number,voter_id_ADDHAAR_number,account_holder_name,account_number,ifsc_code,bank_name;
    TextView shop_name;
    Button  online_payment_btn,deduction_button,cash_payment_btn;
    String shopName,gstNumber,panNumber,voterIdOrAddhaarNumber,accountHolderName,accountNumber,ifscCode,bankName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Payment");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //EditText
        shop_name = (TextView) findViewById(R.id.shop_name);
        gst_number = (EditText) findViewById(R.id.gst_number);
        pan_number = (EditText) findViewById(R.id.pan_number);
        voter_id_ADDHAAR_number = (EditText) findViewById(R.id.voter_id_ADDHAAR_number);
        account_holder_name = (EditText) findViewById(R.id.account_holder_name);
        account_number = (EditText) findViewById(R.id.account_number);
        ifsc_code = (EditText) findViewById(R.id.ifsc_code);
        bank_name = (EditText) findViewById(R.id.bank_name);


        online_payment_btn = (Button) findViewById(R.id.online_payment_btn);
        deduction_button = (Button) findViewById(R.id.deduction_button);
        cash_payment_btn = (Button) findViewById(R.id.cash_payment_btn);

        onClick();

    }

    private void onClick(){

        online_payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!validate()) {
//
//                    Snackbar.make(v, "Please fill above details", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    return;
//                }

                //addMenu();
//                Intent intent = new Intent();
//                setResult(RESULT_OK, intent);
                Intent intent = new Intent(PaymentGatewayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public Boolean validate() {
        boolean valid = true;

//        shopName = shop_name.getText().toString().trim();
//        gstNumber = gst_number.getText().toString().trim();
//        panNumber = pan_number.getText().toString().trim();
//        voterIdOrAddhaarNumber = voter_id_ADDHAAR_number.getText().toString().trim();
//        accountHolderName = account_holder_name.getText().toString().trim();
//        accountNumber = account_number.getText().toString().trim();
//        ifscCode = ifsc_code.getText().toString().trim();
//        bankName = bank_name.getText().toString().trim();

//        if (shopName.trim().isEmpty()) {
//            shop_name.setError("Enter Shop Name");
//            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
//            valid = false;
//        } else {
//            shop_name.setError(null);
//        }

//        if (gstNumber.trim().isEmpty()) {
//            gst_number.setError("Enter Price");
//            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
//            valid = false;
//        } else {
//            gst_number.setError(null);
//        }

        if (panNumber.trim().isEmpty()) {
            pan_number.setError("Enter Pan Number");
            valid = false;
        } else {
            pan_number.setError(null);
        }

        if (voterIdOrAddhaarNumber.trim().isEmpty()) {
            voter_id_ADDHAAR_number.setError("Enter VoterID or Addhaar");
            valid = false;
        } else {
            voter_id_ADDHAAR_number.setError(null);
        }

        if (accountHolderName.trim().isEmpty()) {
            account_holder_name.setError("Enter Account Holder Name");
            valid = false;
        } else {
            account_holder_name.setError(null);
        }

        if (accountNumber.trim().isEmpty()) {
            account_number.setError("Enter Account Number");
            valid = false;
        } else {
            account_number.setError(null);
        }

        if (ifscCode.trim().isEmpty()) {
            ifsc_code.setError("Enter IFSC Code");
            valid = false;
        } else {
            ifsc_code.setError(null);
        }

        if (bankName.trim().isEmpty()) {
            bank_name.setError("Enter Bank Name");
            valid = false;
        } else {
            bank_name.setError(null);
        }


        return valid;
    }
}