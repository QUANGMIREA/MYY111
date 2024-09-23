package com.example.myapplication.api

import com.example.myapplication.Model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("fullname") fullname: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
    @FormUrlEncoded
    @POST("infopet.php")
    fun pushinfoPet(
        @Field("namepet") namepet: String,
        @Field("typeofpet") typeofpet: String,
        @Field("dateofbirth") dateofbirth: String,
        @Field("weight") weight: String,
        @Field("selectgender") selectgender : String,
        @Field("nutrition") nutrition: String
    ): Call<LoginResponse>
}