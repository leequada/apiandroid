package com.example.demoapi.API;
import com.example.demoapi.model.LoginDto;
import com.example.demoapi.model.LoginFacebookDto;
import com.example.demoapi.model.ResponseData;
import com.google.gson.*;
import com.example.demoapi.model.Loan;
import com.example.demoapi.model.MonthLoan;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DataService {


    @GET("auth/getUser-loan")
    Call<List<Loan>>  getUserLoan(@Query("username") String username);

    @GET("auth/getMonthlyLoan")
    Call<List<MonthLoan>> getMonthLoan(@Query("id") int id);


    @POST("auth/donglai")
    Call<JsonObject>donglai(@Query("id") int id);

    @POST("auth/login")
    Call<ResponseData>checkLogin(@Body LoginDto loginDto);

    @POST("auth/login-facebook")
    Call<ResponseData>LoginFacebook(@Body LoginFacebookDto loginFacebook);
}
