package com.instacloud.order2fse.ui.SellerList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.instacloud.order2fse.R;
import com.instacloud.order2fse.Util.CheckNetwork;
import com.instacloud.order2fse.Util.InternetConnection;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.ui.home.DataRestoID;
import com.instacloud.order2fse.ui.home.RestoByAgentIdModel;
import com.instacloud.order2fse.ui.home.ViewRestaurantAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SellerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<SellerListModel> sellerListModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SellerAdapter mAdapter;


    private String extremes = "extremeStorage", type;
    private SharedPreferences token;

    SwipeRefreshLayout swipeRefreshLayout;


    Timer timer;
    String tokenid;


    ViewRestaurantAdapterList viewRestaurantAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_items);

        token = getActivity().getSharedPreferences(extremes,
                Context.MODE_PRIVATE);
        tokenid = token.getString("token", "");
        Log.d("Response: ", tokenid);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_seller_list);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

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
        );

//        mAdapter = new SellerAdapter(sellerListModelList);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);


        return view;

    }


    private void refreshMenu() {
        Retrofit retrofit = RetrofitClient.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.getRestaurantByAgentId("Test", "Test", "100", "56", tokenid);

        call.enqueue(new Callback<RestoByAgentIdModel>() {

            @Override
            public void onResponse(Call<RestoByAgentIdModel> call, Response<RestoByAgentIdModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<DataRestoID> message = response.body().getData();

                        for (int i = 0; i < message.size(); i++) {
                            viewRestaurantAdapter = new ViewRestaurantAdapterList(getContext(), (ArrayList<DataRestoID>) message);
                            recyclerView.setAdapter(viewRestaurantAdapter);
                            recyclerView.invalidate();
                            viewRestaurantAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } else {
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestoByAgentIdModel> call, Throwable t) {
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