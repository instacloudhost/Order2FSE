package com.instacloud.order2fse.ui.Incentives;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.Util.CheckNetwork;
import com.instacloud.order2fse.Util.InternetConnection;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.SellerList.ViewRestaurantAdapterList;
import com.instacloud.order2fse.ui.home.DataShopID;
import com.instacloud.order2fse.ui.home.ShopByAgentIdModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BDEIncentivesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView recyclerView;
    private String extremes = "extremeStorage", type;
    private SharedPreferences token;
    SwipeRefreshLayout swipeRefreshLayout;
    String userID;
    BDEIncentivesAdapter bdeIncentivesAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.b_d_e_incentives_fragment, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_incentives);
        token = getActivity().getSharedPreferences(extremes,
                Context.MODE_PRIVATE);
        userID = token.getString("userID", "");
        Log.d("Response: ", userID);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_incentives);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        if (CheckNetwork.isInternetAvailable(getContext())) //returns true if internet available
                                        {
                                           // refreshMenu();
                                        } else {
                                            Toast.makeText(getContext(), "Please check your Internet Connection and try Again", Toast.LENGTH_SHORT).show();
                                            Intent in = new Intent(getContext(), InternetConnection.class);
                                            startActivity(in);
                                            getActivity().finish();
                                        }
                                    }
                                }
        );

     return view;
    }


    private void refreshMenu() {
        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.getRestaurantByAgentId(userID);

        call.enqueue(new Callback<ShopByAgentIdModel>() {

            @Override
            public void onResponse(Call<ShopByAgentIdModel> call, Response<ShopByAgentIdModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<DataShopID> message = response.body().getData();

                        for (int i = 0; i < message.size(); i++) {
                          //  bdeIncentivesAdapter = new BDEIncentivesAdapter(getContext(), (ArrayList<DataShopID>) message);
                           // recyclerView.setAdapter(bdeIncentivesAdapter);
                           // recyclerView.invalidate();
                           // bdeIncentivesAdapter.notifyDataSetChanged();
                          //  swipeRefreshLayout.setRefreshing(false);
                        }
                    } else {
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShopByAgentIdModel> call, Throwable t) {
                //Toast.makeText(MenuListActivity.this, "Failure " + t, Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onRefresh() {

        //modules.clear();
        if (CheckNetwork.isInternetAvailable(getContext())) //returns true if internet available
        {
            refreshMenu();

        } else {

            Toast.makeText(getContext(), "Please check your Internet Connection and try Again", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getContext(), InternetConnection.class);
            startActivity(in);
            getActivity().finish();
        }

    }





}