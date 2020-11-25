package com.instacloud.order2fse.PushNotification.network;

import com.instacloud.order2fse.PushNotification.model.RequestNotificaton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.instacloud.order2fse.PushNotification.utils.AppConstants.SERVER_KEY;

public interface BaseApiService {

    @Headers({"Authorization: key=" + SERVER_KEY,
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Body RequestNotificaton requestNotificaton);


}
