package com.instacloud.order2fse.ui.Item.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.Util.CheckNetwork;
import com.instacloud.order2fse.Util.InternetConnection;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.ui.Item.Model.AddMenuModel;
import com.instacloud.order2fse.ui.Item.Model.DataAddMenu;
import com.instacloud.order2fse.ui.Item.Adapters.ItemAdapter;
import com.instacloud.order2fse.ui.Item.Adapters.ItemViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ItemListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    private static final String TAG = "MenuListActivity";

    //private List<MenuListModel> menuListModels;
    private RecyclerView recyclerView;
    ItemAdapter mAdapter;

    ItemViewAdapter menuItemViewAdapter;
    private ActionBar toolbar;
    private Button addMenuButton;

    String foodId;


    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();


    String restaurantId;

    private String extremes = "extremeStorage", type;
    private SharedPreferences token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText("Item List");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        token = getSharedPreferences(extremes,
                Context.MODE_PRIVATE);


        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       // menuListModels = new ArrayList<>();

        Intent intent = getIntent();
        restaurantId = intent.getStringExtra("id");
        addMenuButton =(Button) findViewById(R.id.addMenuButton);
        addMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);
                startActivityForResult(intent, 10);
                finish();
            }
        });

        ImageView button = (ImageView) findViewById(R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        if (CheckNetwork.isInternetAvailable(ItemListActivity.this)) //returns true if internet available
                                        {
                                            refreshMenu();

                                        } else {

                                            Toast.makeText(ItemListActivity.this, "Please check your Internet Connection and try Again", Toast.LENGTH_SHORT).show();
                                            Intent in = new Intent(ItemListActivity.this, InternetConnection.class);
                                            startActivity(in);
                                            finish();
                                        }

                                    }
                                }
        );



        //refreshMenu();

    }

    private void refreshMenu() {
        Retrofit retrofit = RetrofitClient.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.addMenuView(token.getString("restaurant_id",""));

        call.enqueue(new Callback<AddMenuModel>() {

            @Override
            public void onResponse(Call<AddMenuModel> call, Response<AddMenuModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess().equals(true)) {
                        List<DataAddMenu> message = response.body().getData();

                        for (int i = 0; i < message.size(); i++){


                            foodId = String.valueOf(message.get(i).getId());
                            SharedPreferences.Editor editor = token.edit();
                            editor.putString("food_id", String.valueOf(foodId));
                            editor.commit();

                            menuItemViewAdapter = new ItemViewAdapter(getApplicationContext(), (ArrayList<DataAddMenu>) message);
                            recyclerView.setAdapter(menuItemViewAdapter);
                            recyclerView.invalidate();
                            menuItemViewAdapter.notifyItemChanged(i);
                            menuItemViewAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                    else {
                        Toast.makeText(ItemListActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddMenuModel> call, Throwable t) {
                //Toast.makeText(MenuListActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                refreshMenu();
            }
        }
    }

//    @Override
//    public void onBackPressed()
//    {
//        startActivity(new Intent(this, MenuListActivity.class));
//
//        super.onBackPressed();  // optional depending on your needs
//    }


    @Override
    public void onRefresh() {

        //modules.clear();
        if (CheckNetwork.isInternetAvailable(ItemListActivity.this)) //returns true if internet available
        {
            refreshMenu();

        } else {

            Toast.makeText(ItemListActivity.this, "Please check your Internet Connection and try Again", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(ItemListActivity.this, InternetConnection.class);
            startActivity(in);
            finish();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}