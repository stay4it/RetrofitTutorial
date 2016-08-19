package com.stay4it.retrofit.api.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stay4it.retrofit.api.APIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    /**
     * 服务器地址
     */
    private static final String API_HOST = "http://api.stay4it.com/";

    private static Retrofit mRetrofit;
    private static APIService mAPIService;

    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // Log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = builder.addInterceptor(logging)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_HOST)
                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofit;
    }

    public static APIService getAPIService() {
        if (mAPIService == null) {
            mAPIService = getRetrofit().create(APIService.class);
        }
        return mAPIService;
    }


    public static Gson buildGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
                .registerTypeAdapter(int.class, new IntegerDefaultAdapter())
                .create();
    }


}
