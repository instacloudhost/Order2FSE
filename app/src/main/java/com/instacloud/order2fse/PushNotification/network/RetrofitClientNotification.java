package com.instacloud.order2fse.PushNotification.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.instacloud.order2fse.PushNotification.utils.AppConstants.BASE_URL1;
import static com.instacloud.order2fse.remote.RetrofitClient.BASE_URL;


public class RetrofitClientNotification {


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}