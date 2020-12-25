package com.instacloud.order2fse.ui.Item.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.Item.Model.AddMenuModel;
import com.instacloud.order2fse.ui.Item.Model.CategoryModel;
import com.instacloud.order2fse.ui.Item.Model.Datum;
import com.instacloud.order2fse.ui.Item.Model.ProductsModel.ItemDatum;
import com.instacloud.order2fse.ui.Item.Model.ProductsModel.ItemModel;
import com.instacloud.order2fse.ui.Item.Model.SubCategoryModel.SubCategoryDatum;
import com.instacloud.order2fse.ui.Item.Model.SubCategoryModel.SubCategoryModel;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddItemActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText item_name, item_price, item_discount, item_description, item_Specification, item_package_count, item_weight;
    BetterSpinner unitSpinner, mainCategorySpinner, itemCategorySpinner, itemsSpinner;
    ImageView item_image;
    Button add_items_Button;
    String itemName, itemPrice, itemDiscount, itemDescription, itemIngredients, itemUnit, itemPackageCount, itemWeight;
    String restaurantsId;
    String mainCategoryId, itemCategoryId, itemsId;
    String[] UNITS = {"KG", "LITERS", "PIECES", "PACKET"};
    String[] ItemCategory = {"INDIAN", "RICE", "SEAFOOD", "BEVERAGES", "TANDOOR",
            "SALAD/PAPAD", "BREAKFAST", "DESSERTS", "BREAD BASKET", "SOUP", "STARTERS", "APPETIZERS", "NOODLES"};
    String[] It = {"KG", "LITERS", "PIECES", "PACKET"};
    private String[] Items;
    private ArrayAdapter<String> adapterItemCategory, adapterItems;
    String itemsName, shopId;
    private String extremes = "extremeStorage", type;
    private SharedPreferences token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);


        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText("Add Item");
        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent2 = getIntent();
        intent2.putExtra("shopID",shopId);

        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");

        //EditText
        item_name = (EditText) findViewById(R.id.food_name);
        item_price = (EditText) findViewById(R.id.food_price);
        item_discount = (EditText) findViewById(R.id.food_discount);
        item_description = (EditText) findViewById(R.id.item_description);
        item_Specification = (EditText) findViewById(R.id.item_Specification);
        item_package_count = (EditText) findViewById(R.id.item_package_count);
        item_weight = (EditText) findViewById(R.id.item_weight);

        //spinners
        itemCategorySpinner = (BetterSpinner) findViewById(R.id.subCategorySpinner);

        unitSpinner = (BetterSpinner) findViewById(R.id.unitSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_dropdown_item_1line, UNITS);
        unitSpinner.setAdapter(adapter);
        mainCategorySpinner = (BetterSpinner) findViewById(R.id.categorySpinner);

        //ImageViewFood
        item_image = findViewById(R.id.food_image);

        //AddMenuButton
        add_items_Button = (Button) findViewById(R.id.add_food_menu_Button);

        add_items_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }

                addMenu();

                Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);
                intent.putExtra("id",shopId);
                startActivity(intent);
                finish();
            }
        });

        callMainCategory();
        callSubCategory();
        //callProducts(itemCategoryId);

    }


    public void callMainCategory() {

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addCategory();

        call.enqueue(new Callback<CategoryModel>() {

            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<Datum> categoryModels = response.body().getData();


                        showListInSpinner(categoryModels);
                    } else {
                        Toast.makeText(AddItemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void callSubCategory() {

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addSubCategory();

        call.enqueue(new Callback<SubCategoryModel>() {

            @Override
            public void onResponse(Call<SubCategoryModel> call, Response<SubCategoryModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<SubCategoryDatum> subCategoryDatumList = response.body().getData();


                        showListItemCategoryInSpinner(subCategoryDatumList);
                    } else {
                        Toast.makeText(AddItemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubCategoryModel> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void addMenu() {

        progressBar();
        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addMenu(itemName, itemPrice, itemDiscount, itemDescription,
                itemIngredients, itemWeight, itemPackageCount, itemUnit, "true", "true", shopId, mainCategoryId);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() != null) {
                    AddMenuModel addMenuModel = (AddMenuModel) response.body();
                    Log.d("Response: ", String.valueOf(addMenuModel.getMessage()));
                    if (addMenuModel.getSuccess().equals("true")) {


                        progressDialog.cancel();
                    } else {
                        //   progressDialog.cancel();
                        //  Toast.makeText(AddMenuActivity.this, addMenuModel.getData(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //Toast.makeText(AddMenuActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error: ", t.getMessage());
            }
        });


    }


    //Our method to show list
    private void showListInSpinner(List<Datum> categoryModels) {
        //String array to store all the book names
        String[] items = new String[categoryModels.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < categoryModels.size(); i++) {
            //Storing names to string array
            items[i] = categoryModels.get(i).getName();
        }

        mainCategorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Getting State", parent.getItemAtPosition(position).toString());
                String count = parent.getItemAtPosition(position).toString();
                mainCategoryId = String.valueOf(categoryModels.get(position).getId());

                // district(items);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_dropdown_item_1line, items);

        mainCategorySpinner.setAdapter(adapter);


    }

    //Our method to show list
    private void showListItemCategoryInSpinner(List<SubCategoryDatum> categoryModels) {
        //String array to store all the book names
        String[] itemsSubCategory = new String[categoryModels.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < categoryModels.size(); i++) {
            //Storing names to string array
            itemsSubCategory[i] = categoryModels.get(i).getName();
        }

        itemCategorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Getting State", parent.getItemAtPosition(position).toString());
                String count = parent.getItemAtPosition(position).toString();
                itemCategoryId = String.valueOf(categoryModels.get(position).getId());
                callProducts(itemCategoryId);
            }
        });
        adapterItemCategory = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_dropdown_item_1line, itemsSubCategory);
        itemCategorySpinner.setAdapter(adapterItemCategory);

    }

    public void callProducts(String itemCategoryId) {

        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addProducts(itemCategoryId);

        call.enqueue(new Callback<ItemModel>() {

            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<ItemDatum> itemDatumList = response.body().getData();


                        showListItemsInSpinner(itemDatumList);
                    } else {
                        Toast.makeText(AddItemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Our method to show list
    private void showListItemsInSpinner(List<ItemDatum> itemDatumList) {
        //String array to store all the book names
        String[] itemsProduct = new String[itemDatumList.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < itemDatumList.size(); i++) {
            //Storing names to string array
            itemsProduct[i] = itemDatumList.get(i).getName();
        }


        String[] ITEMS2 = {"No Items"};
        itemsSpinner = (BetterSpinner) findViewById(R.id.itemsSpinner);
        adapterItems = new ArrayAdapter<String>(AddItemActivity.this, android.R.layout.simple_dropdown_item_1line, itemsProduct);
        itemsSpinner.setAdapter(adapterItems);


        itemsSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Getting State", parent.getItemAtPosition(position).toString());
                String productName = parent.getItemAtPosition(position).toString();
                product(productName);
            }
        });


    }

    private void product(String productName) {

        item_name.setText(productName);

    }


    public Boolean validate() {
        boolean valid = true;

        itemName = item_name.getText().toString().trim();
        itemPrice = item_price.getText().toString().trim();
        itemDiscount = item_discount.getText().toString().trim();
        itemDescription = item_description.getText().toString().trim();
        itemIngredients = item_Specification.getText().toString().trim();
        itemUnit = unitSpinner.getText().toString().trim();
        itemPackageCount = item_package_count.getText().toString().trim();
        itemWeight = item_weight.getText().toString().trim();

        if (itemName.trim().isEmpty()) {
            item_name.setError("Enter Item Name");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            item_name.setError(null);
        }

        if (itemPrice.trim().isEmpty()) {
            item_price.setError("Enter Price");
            //Toast.makeText(this, "Enter Travel Allowance.", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            item_price.setError(null);
        }

        if (itemDiscount.trim().isEmpty()) {
            item_discount.setError("Enter Discount");
            valid = false;
        } else {
            item_discount.setError(null);
        }

        if (itemDescription.trim().isEmpty()) {
            item_description.setError("Enter Description");
            valid = false;
        } else {
            item_description.setError(null);
        }

        if (itemIngredients.trim().isEmpty()) {
            item_Specification.setError("Enter Specification");
            valid = false;
        } else {
            item_Specification.setError(null);
        }

        if (itemUnit.trim().isEmpty()) {
            unitSpinner.setError("Enter Item Unit");
            valid = false;
        } else {
            unitSpinner.setError(null);
        }

        if (itemPackageCount.trim().isEmpty()) {
            item_package_count.setError("Enter Package Count");
            valid = false;
        } else {
            item_package_count.setError(null);
        }

        if (itemWeight.trim().isEmpty()) {
            item_weight.setError("Enter Item Weight");
            valid = false;
        } else {
            item_weight.setError(null);
        }


        return valid;
    }


    @Override
    public void onBackPressed() {
        // code here to show dialog

        Intent intentBack = new Intent(AddItemActivity.this, ItemListActivity.class);
        intentBack.putExtra("id",shopId);
        startActivity(intentBack);
        finish();
        super.onBackPressed();  // optional depending on your needs
    }

    private void progressBar() {
        progressDialog = new ProgressDialog(AddItemActivity.this);
        progressDialog.setTitle("Just a moment");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }


}