package com.instacloud.order2fse.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {

    public static final String BASE_URL2 = "https://order2.in";
    public static Retrofit retrofit;

    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */


    public static Retrofit getRetrofitOrder() {
        //If condition to ensure we don't create multiple retrofit instances in a single application
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient2 = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
            httpClient2.addInterceptor(logging);  // <-- this is the important line!
            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .client(httpClient2.build())
                    .build();
        }
        return retrofit;
    }



}
