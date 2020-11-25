package com.instacloud.order2fse.ui.ShopDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.instacloud.order2fse.R;

import org.w3c.dom.Text;

public class AboutShopActivity extends AppCompatActivity {

    TextView shopId,shop_name,shop_description,shop_address,latitude,longitude,phone,mobile,information,adminCommission,deliveryFee,deliveryRange;
    String ShopId,Shop_name,Shop_description,Shop_address,Latitude,Longitude,Phone,Mobile,Information,AdminCommission,DeliveryFee,DeliveryRange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_shop);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Shop Details");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //TextView



        Intent intent = getIntent();
        Shop_name = intent.getStringExtra("name");
        Shop_description = intent.getStringExtra("description");
        Shop_address = intent.getStringExtra("address");
        Latitude = intent.getStringExtra("latitude");
        Longitude = intent.getStringExtra("longitude");
        Phone = intent.getStringExtra("phone");
        Mobile = intent.getStringExtra("mobile");
        Information = intent.getStringExtra("information");
        AdminCommission = intent.getStringExtra("admin_commission");
        DeliveryFee = intent.getStringExtra("delivery_fee");
        DeliveryRange = intent.getStringExtra("delivery_range");

        shop_name = (TextView) findViewById(R.id.shop_name);
        shop_description = (TextView) findViewById(R.id.shop_description);
        shop_address = (TextView) findViewById(R.id.shop_address);
        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        phone = (TextView) findViewById(R.id.phone);
        mobile = (TextView) findViewById(R.id.mobile);
        information = (TextView) findViewById(R.id.information);
        adminCommission = (TextView) findViewById(R.id.adminCommission);
        deliveryFee = (TextView) findViewById(R.id.deliveryFee);
        deliveryRange = (TextView) findViewById(R.id.deliveryRange);


        shop_name.setText(Shop_name);
        shop_description.setText(Shop_description);
        shop_address.setText(Shop_address);
        latitude.setText(Latitude);
        longitude.setText(Longitude);
        phone.setText(Phone);
        mobile.setText(Mobile);
        information.setText(Information);
        adminCommission.setText("Commission "+AdminCommission+" %");
        deliveryFee.setText("Rs. "+DeliveryFee+" /-");
        deliveryRange.setText(DeliveryRange + "KM");



    }
}