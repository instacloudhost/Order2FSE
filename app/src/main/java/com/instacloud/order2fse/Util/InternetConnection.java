package com.instacloud.order2fse.Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.ui.Item.Activity.ItemListActivity;
import com.instacloud.order2fse.ui.Login.LoginActivity;


public class InternetConnection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_connection);

        Button try_again = findViewById(R.id.try_again);

        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckNetwork.isInternetAvailable(InternetConnection.this)) //returns true if internet available
                {
                    Intent in = new Intent(InternetConnection.this, LoginActivity.class);
                    Toast.makeText(InternetConnection.this,"You are ONLINE",Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();

                }else
                {
                    Toast.makeText(InternetConnection.this,"Please check your Internet Connection and try Again",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
