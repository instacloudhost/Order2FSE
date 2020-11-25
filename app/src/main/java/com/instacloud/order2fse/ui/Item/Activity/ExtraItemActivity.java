package com.instacloud.order2fse.ui.Item.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.Item.Model.DataExtraGroup;
import com.instacloud.order2fse.ui.Item.Model.ExtraGroupIDModel;
import com.instacloud.order2fse.ui.Item.Model.ExtraItemModel;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExtraItemActivity extends AppCompatActivity {

    EditText extra_name, extra_description, extra_price;
    Button add_extra_menu_Button;
    ImageView extra_image;
    BetterSpinner extraFoodIdSpinner, extraGroupIdSpinner;
    TextView fooName;
    String extraName,extraDescription,extraPrice,extraFoodId, foodId,foodIDGet;
    String[] extraGroupId;
    private String extremes = "extremeStorage", type;
    private SharedPreferences token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Add Extra");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //RestaurantId
        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);


        Intent intent = getIntent();
        foodId = intent.getStringExtra("food_Id");

        //Demo TextView
        fooName = (TextView)findViewById(R.id.food_name);
        fooName.setText(foodId);
        foodIDGet = fooName.getText().toString().trim();
        //EditText
        extra_name = (EditText) findViewById(R.id.extra_name);
        extra_description = (EditText) findViewById(R.id.extra_description);
        extra_price = (EditText) findViewById(R.id.extra_price);
        //spinners
        extraFoodIdSpinner = (BetterSpinner) findViewById(R.id.extraFoodIdSpinner);
        extraGroupIdSpinner = (BetterSpinner) findViewById(R.id.extraGroupIdSpinner);
        //ImageViewFood
        extra_image = findViewById(R.id.extra_image);
        //AddMenuButton
        add_extra_menu_Button = (Button) findViewById(R.id.add_extra_menu_Button);
        add_extra_menu_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {

                    Snackbar.make(v, "Enter above details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                addExtra();
                Intent intent = new Intent(ExtraItemActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        callCategory();


    }

    public Boolean validate() {
        boolean valid = true;

        extraName = extra_name.getText().toString().trim();
        extraDescription = extra_description.getText().toString().trim();
        extraPrice = extra_price.getText().toString().trim();


        if (extraName.trim().isEmpty()) {
            extra_name.setError("Enter Name");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            extra_name.setError(null);
        }

        if (extraDescription.trim().isEmpty()) {
            extra_description.setError("Enter Description");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            extra_description.setError(null);
        }

        if (extraPrice.trim().isEmpty()) {
            extra_price.setError("Enter Price");
            valid = false;
        } else {
            extra_price.setError(null);
        }


        return valid;
    }


    public void callCategory() {

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addExtraGroupID();

        call.enqueue(new Callback<ExtraGroupIDModel>() {

            @Override
            public void onResponse(Call<ExtraGroupIDModel> call, Response<ExtraGroupIDModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<DataExtraGroup> extraGroupList = response.body().getData();


                        showListinSpinner(extraGroupList);
                    } else {
                        Toast.makeText(ExtraItemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ExtraGroupIDModel> call, Throwable t) {
                Toast.makeText(ExtraItemActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Our method to show list
    private void showListinSpinner(List<DataExtraGroup> extraGroupList) {
        //String array to store all the book names
        String[] items = new String[extraGroupList.size()];
        extraGroupId = new String[extraGroupList.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < extraGroupList.size(); i++) {
            //Storing names to string array
            items[i] = extraGroupList.get(i).getName();
            items[i] = String.valueOf(extraGroupList.get(i).getId());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExtraItemActivity.this, android.R.layout.simple_dropdown_item_1line, items);

        extraGroupIdSpinner.setAdapter(adapter);

    }

    public void addExtra() {
        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addExtraItems(extraName, extraDescription,extraPrice,foodIDGet,extraGroupId);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    ExtraItemModel extraItemModel = (ExtraItemModel) response.body();
                    Log.d("Response: ", String.valueOf(extraItemModel.getMessage()));
                    if (extraItemModel.getSuccess().equals("true")) {




                    } else {
                        //   progressDialog.cancel();
                        //  Toast.makeText(AddMenuActivity.this, addMenuModel.getData(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(ExtraItemActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }

//
//        @Override
//    public void onBackPressed()
//    {
//        startActivity(new Intent(this, MenuListActivity.class));
//
//        //super.onBackPressed();  // optional depending on your needs
//    }



}