package com.instacloud.order2fse.ui.Login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.foreground.Tracking;
import com.instacloud.order2fse.model.LoginModel;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.Login.Model.Order2LoginModel;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextInputEditText email_id, password;
    private SharedPreferences token;
    private String extremes = "extremeStorage";
    private String type;
    Button btn_login;
    String mail, pass;
    String tokenid,deviceToken;
    public Tracking gpsService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        permit();
        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);

        tokenid = token.getString("token", "");
        Log.d("Response: ", tokenid);

        deviceToken = token.getString("device_token", "");
        Log.d("Response: ", deviceToken);


        final Intent intent = new Intent(this.getApplication(), Tracking.class);
        this.getApplication().startService(intent);
        this.getApplication().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        SharedPreferences sharedpreferences = getSharedPreferences(extremes, Context.MODE_PRIVATE);
        if (sharedpreferences.getLong("ExpiredDate", -1) > System.currentTimeMillis()) {
            // read email and password

            if (token.contains("token")) {
                Intent dashboard = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(dashboard);
                // Toast.makeText(getApplicationContext(), "Successfully LoggedIn", Toast.LENGTH_LONG).show();
                finish();
            }


        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.apply();

            //   stopService();
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

                getFirebaseMessagingToken();


                }
        });


    }

    public Boolean validate() {
        boolean valid = true;

        mail = email_id.getText().toString().trim();
        pass = password.getText().toString().trim();


        if (mail.trim().isEmpty()) {
            email_id.setError("Enter Email");
            email_id.requestFocus();
            valid = false;
        } else {
            email_id.setError(null);

        }

        if (pass.trim().isEmpty()) {
            password.setError("Enter Password");
            password.requestFocus();
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    private void getLoginDetails(String fireBase_token) {

        progressBar();
        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.checkUserOrder2(mail,pass,fireBase_token);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    Order2LoginModel loginModel = (Order2LoginModel) response.body();
                    Log.d("Response: ", String.valueOf(loginModel.getMessage()));
                    if (loginModel.getSuccess().equals(true)) {


                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("token", loginModel.getData().getApiToken());
                        editor.putString("userName",loginModel.getData().getName());
                        editor.putString("userEmail",loginModel.getData().getEmail());
                        editor.putString("userID", String.valueOf(loginModel.getData().getId()));
                        editor.putLong("ExpiredDate", System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(100));
                        editor.apply();

                        if (token.contains("token") && token.contains("userID")) {
//


                            startService(tokenid);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(main);
                            finish();

                        }
                        else {

                           // Toast.makeText(LoginActivity.this, "Token & UserID not Available", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressDialog.cancel();
                        Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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


    public void permit() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this,"Permissions Granted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(LoginActivity.this, "Permissions Not Granted", Toast.LENGTH_LONG).show();
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                TedPermission.with(LoginActivity.this)
                        .setPermissionListener(permissionListener)
                        .setPermissions(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                                Manifest.permission.FOREGROUND_SERVICE,
                                Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION

                        ).check();
            }
        }
    }


    // Start the service
    public void startService(String tokenid){
        gpsService.startTracking(tokenid);
    }

    // Stop the service
    public void stopService(){
        gpsService.stopTracking();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            String name = className.getClassName();
            if (name.endsWith("Tracking")) {
                gpsService = ((Tracking.LocationServiceBinder) service).getService();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            if (className.getClassName().equals("Tracking")) {
                gpsService = null;
            }
        }
    };


    public void getFirebaseMessagingToken ( ) {
        FirebaseMessaging.getInstance ().getToken ()
                .addOnCompleteListener ( task -> {
                    if (!task.isSuccessful ()) {
                        //Could not get FirebaseMessagingToken
                        return;
                    }
                    if (null != task.getResult ()) {
                        //Got FirebaseMessagingToken
                        String firebaseMessagingToken = Objects.requireNonNull ( task.getResult () );
                      //  Toast.makeText(LoginActivity.this, "FCM_Token - " + firebaseMessagingToken, Toast.LENGTH_LONG).show();
                        Log.d("MYTAG", "This is your Firebase token - " + firebaseMessagingToken);


                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("device_token", firebaseMessagingToken);
                        editor.apply();

                        String fireBase_token = firebaseMessagingToken;


                        if (token.contains("device_token")){

                            getLoginDetails(fireBase_token);

                        }
                        //Use firebaseMessagingToken further


                    }
                } );
    }

}
