package com.example.myapplication.api

import com.example.myapplication.Model.CartResponse
import com.example.myapplication.Model.LoginResponse
import com.example.myapplication.Model.ProductsApiResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Field("user_id") userId: Int,  // Thêm user_id
        @Field("namepet") namepet: String,
        @Field("typeofpet") typeofpet: String,
        @Field("dateofbirth") dateofbirth: String,
        @Field("weight") weight: String,
        @Field("selectgender") selectgender : String,
        @Field("nutrition") nutrition: String
    ): Call<LoginResponse>

    @GET("products.php") // Đảm bảo đường dẫn đúng với API của bạn
    fun getProducts(): Call<ProductsApiResponse>
    @GET("products")
    fun getProductsByType(@Query("type") type: String): Call<ProductsApiResponse>

    @FormUrlEncoded
    @POST("upload_cart.php")
    fun uploadCart(
        @Field("user_id") userId: Int,
        @Field("cart_items") cartItems: String
    ): Call<LoginResponse>
    @GET("get_cart.php")
    fun getCartItems(
        @Query("user_id") userId: Int
    ): Call<CartResponse>
    @GET("cart_state.php")
    fun getCartState(
        @Query("user_id") userId: Int
    ): Call<CartResponse>
    @FormUrlEncoded
    @POST("update_cart_item.php") // Đảm bảo đường dẫn đúng với endpoint PHP của bạn
    fun updateCartItemQuantity(
        @Field("user_id") userId: Int,
        @Field("product_id") productId: Int,
        @Field("new_quantity") newQuantity: Int
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("infoorder.php")
    fun pushinfoorder(
        @Field("user_id") userId: Int,  // Thêm user_id
        @Field("nameuser") nameuser: String,
        @Field("numbertelephone") numbertelephone : String,
        @Field("adress") adress : String,
        @Field("numberroom2") numberroom2 : String,
        @Field("numberroom3") numberroom3 : String,
    ): Call<LoginResponse>

}