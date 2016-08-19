package com.stay4it.retrofit.api;

import com.stay4it.retrofit.bean.HttpResponse;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * 接口定义
 * @author lizhixian
 * @time 16/8/18 21:46
 */
public interface APIService {

    @Multipart
    @POST("v1/public/core/?service=user.updateAvatar")
    Observable<HttpResponse<List<String>>> uploadMultipleTypeFile(@Part("data") String des, @PartMap Map<String,RequestBody> params);

}
