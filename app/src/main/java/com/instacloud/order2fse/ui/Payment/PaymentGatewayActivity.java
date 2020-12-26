package com.instacloud.order2fse.ui.Payment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.AddShop.Model.AddManagerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentGatewayActivity extends AppCompatActivity {

    EditText gst_number, pan_number, voter_id_ADDHAAR_number, account_holder_name, account_number, ifsc_code, bank_name;
    TextView shop_name;
    private ProgressDialog progressDialog;
    Button online_payment_btn, deduction_button, cash_payment_btn;
    String restaurant_Id, agentCommission, userId, voterIdOrAddhaarNumber, accountHolderName, accountNumber, ifscCode, bankName;
    private SharedPreferences token;
    private String extremes = "extremeStorage", type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);


        userId = token.getString("userID", "");
        Log.d("Response: ", userId);

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

        Intent intent = getIntent();

        restaurant_Id = intent.getStringExtra("Shop_id");
        agentCommission = intent.getStringExtra("agentCommission");

       // restaurant_Id = "22";

        online_payment_btn = (Button) findViewById(R.id.online_payment_btn);
        deduction_button = (Button) findViewById(R.id.deduction_button);
        cash_payment_btn = (Button) findViewById(R.id.cash_payment_btn);

        online_payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    sendOnlinePayment();




            }
        });


        deduction_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    sendDeduction();

            }
        });


        cash_payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    sendCashPayment();





            }
        });


    }



    public void sendOnlinePayment() {
        progressBar();
        String onlinePayment = "Online Payment";

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.sendPaymentMode(restaurant_Id,onlinePayment);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful()) {
                    PaymentModel paymentModel = response.body();
                    Log.d("Response: ", String.valueOf(paymentModel.getMessage()));

                    sendAgentCommission();

                    Toast.makeText(PaymentGatewayActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentGatewayActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    progressDialog.cancel();

                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(PaymentGatewayActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }


    public void sendDeduction() {
        progressBar();
        String deduction = "Deduction";

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.sendPaymentMode(restaurant_Id,deduction);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful()) {
                    PaymentModel paymentModel = response.body();
                    Log.d("Response: ", String.valueOf(paymentModel.getMessage()));

                    sendAgentCommission();
                    progressDialog.cancel();

                    Toast.makeText(PaymentGatewayActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentGatewayActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(PaymentGatewayActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }


    public void sendCashPayment() {
        progressBar();
        String cashPayment = "Cash Payment";

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.sendPaymentMode(restaurant_Id,cashPayment);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful()) {
                    PaymentModel paymentModel = response.body();
                    Log.d("Response: ", String.valueOf(paymentModel.getMessage()));


                    sendAgentCommission();

                    progressDialog.cancel();

                    Toast.makeText(PaymentGatewayActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentGatewayActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(PaymentGatewayActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }


    public void sendAgentCommission() {
        progressBar();
        //String deduction = "Deduction";

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.sendAgentCommission(userId,restaurant_Id,agentCommission);

        call.enqueue(new Callback<AgentCommissionModel>() {
            @Override
            public void onResponse(Call<AgentCommissionModel> call, Response<AgentCommissionModel> response) {
                if (response.isSuccessful()) {
                    AgentCommissionModel agentCommissionModel = response.body();
                    Log.d("Response: ", String.valueOf(agentCommissionModel.getMessage()));

                    progressDialog.cancel();

                    Toast.makeText(PaymentGatewayActivity.this, "Agent Commission Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentGatewayActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<AgentCommissionModel> call, Throwable t) {
                Toast.makeText(PaymentGatewayActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }



    private void progressBar() {
        progressDialog = new ProgressDialog(PaymentGatewayActivity.this);
        progressDialog.setTitle("Just a moment");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }


}