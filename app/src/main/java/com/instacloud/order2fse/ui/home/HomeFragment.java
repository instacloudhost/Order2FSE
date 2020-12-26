package com.instacloud.order2fse.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.instacloud.order2fse.MainActivity;
import com.instacloud.order2fse.PushNotification.model.RequestNotificaton;
import com.instacloud.order2fse.PushNotification.model.SendNotificationModel;
import com.instacloud.order2fse.PushNotification.network.BaseApiService;
import com.instacloud.order2fse.PushNotification.network.RetrofitClientNotification;
import com.instacloud.order2fse.R;
import com.instacloud.order2fse.Util.CheckNetwork;
import com.instacloud.order2fse.Util.InternetConnection;
import com.instacloud.order2fse.foreground.Tracking;
import com.instacloud.order2fse.remote.APIService;
import com.instacloud.order2fse.remote.RetrofitClient;
import com.instacloud.order2fse.remote.RetrofitClient2;
import com.instacloud.order2fse.ui.Login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private String device_token;
    private BaseApiService apiService;


    private RecyclerView recyclerView;
    private String extremes = "extremeStorage", type;
    private SharedPreferences token;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    String user_id;
    ViewShopAdapter viewShopAdapter;

    TextView totalReports;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        token = requireContext().getSharedPreferences(extremes,
                Context.MODE_PRIVATE);
        user_id = token.getString("userID", "");
        Log.d("Response: ", user_id);




        totalReports = root.findViewById(R.id.todaysReport);

        totalReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        if (token.contains("token")) {

            refreshMenu();



        }


        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout_home);
        recyclerView = root.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
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


        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                device_token = instanceIdResult.getToken();
            }
        });

        final EditText etTitle = root.findViewById(R.id.etTitle);
        final EditText etDesc = root.findViewById(R.id.etDesc);
        Button btnHit = root.findViewById(R.id.btnHit);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesAndHitServer(etTitle, etDesc);
            }
        });


        return root;
    }


    private void refreshMenu() {
        Retrofit retrofit = RetrofitClient2.getRetrofitOrder();
        APIService apiservice = retrofit.create(APIService.class);
        Call call = apiservice.getRestaurantByAgentId(user_id);

        call.enqueue(new Callback<ShopByAgentIdModel>() {

            @Override
            public void onResponse(Call<ShopByAgentIdModel> call, Response<ShopByAgentIdModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals(true)) {
                        List<DataShopID> message = response.body().getData();

                        for (int i = 0; i < message.size(); i++) {
                            viewShopAdapter = new ViewShopAdapter(getContext(), (ArrayList<DataShopID>) message);
                            recyclerView.setAdapter(viewShopAdapter);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.invalidate();
                            viewShopAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } else {

                        swipeRefreshLayout.setRefreshing(true);
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShopByAgentIdModel> call, Throwable t) {
              //  Toast.makeText(getContext(), "Failure " + t, Toast.LENGTH_SHORT).show();
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


    private void getValuesAndHitServer(EditText etTitle, EditText etDesc) {
        String title = etTitle.getText().toString();
        String desc = etDesc.getText().toString();


        sendNotificationToPatner(title, desc);

    }

    private void sendNotificationToPatner(String title, String desc) {

        SendNotificationModel sendNotificationModel = new SendNotificationModel(title, desc);
        RequestNotificaton requestNotificaton = new RequestNotificaton();
        requestNotificaton.setSendNotificationModel(sendNotificationModel);
        //token is id , whom you want to send notification ,
        requestNotificaton.setToken(device_token);

        apiService = RetrofitClientNotification.getClient().create(BaseApiService.class);
        retrofit2.Call<ResponseBody> responseBodyCall = apiService.sendChatNotification(requestNotificaton);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d("kkkk", "done");
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }




}