package com.instacloud.order2fse.ui.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.model.LoginModel;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.ui.Item.Activity.ItemListActivity;
import com.instacloud.order2fse.ui.ShopDetails.ShopFormsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private EditText email_id, password;
    private SharedPreferences token;
    private String extremes = "extremeStorage";
    private String type;
    Button btn_login;
    String mail, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);

        String tokenid = token.getString("token", "");
        Log.d("Response: ", tokenid);


        if (token.contains("token")) {
            Intent dashboard = new Intent(LoginActivity.this, ItemListActivity.class);
            startActivity(dashboard);
           // Toast.makeText(getApplicationContext(), "Successfully LoggedIn", Toast.LENGTH_LONG).show();
            finish();
        }

        btn_login = (Button) findViewById(R.id.login);
        email_id = findViewById(R.id.usernameEmail);
        password = findViewById(R.id.userPassword);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    //onNextFailed();
                    Snackbar.make(v, "Enter above details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                getLoginDetails();




            }
        });


    }

    public Boolean validate() {
        boolean valid = true;

        mail = email_id.getText().toString().trim();
        pass = password.getText().toString().trim();

        if (mail.trim().isEmpty()) {
            email_id.setError("Enter Email");
            valid = false;
        } else {
            email_id.setError(null);
        }

        if (pass.trim().isEmpty()) {
            password.setError("Enter Password");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    private void getLoginDetails() {

        progressBar();
        Retrofit retrofit = RetrofitClient.getRetrofitAuth();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.checkUser(mail, pass, "fse");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    LoginModel loginModel = (LoginModel) response.body();
                    Log.d("Response: ", String.valueOf(loginModel.getToken()));
                    if (loginModel.getStatus().equals("true")) {
                       progressDialog.cancel();

                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("token", loginModel.getToken());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(main);
                        finish();
                    } else {
                       progressDialog.cancel();
                        Toast.makeText(LoginActivity.this,"Wrong Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });
    }


    private void progressBar() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Authenticating");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

}
