package com.example.myapplication.Model

data class ProductsApiResponse(
    val success: Boolean,
    val message: String,
    val result: List<Product>
)

data class Product(
    val id: String,
    val hinhanh: String,
    val tensanpham: String,
    val motasanpham: String,
    val hansudung: String,
    val giasp: String,
    val type: String,
    var soluong: Int
)
data class CartItem(
    val product_id: Int,
    val product_name: String,
    val hinhanh: String,
    val price: Double,
    val quantity: Int,
    val total: Double
)
data class CartResponse(
    val success: Boolean,
    val message: String,
    val data: List<CartItem>,
    val cart_state: CartState
)
data class CartState(
    val total: Double =0.0,
    val tax: Double=0.0,
    val delivery_fee: Double=0.0,
    val discount: Double=0.0,
    val final_total: Double=0.0
)
data class InfoOrder(
    val user_id: Int,
    val nameuser: String,
    val numbertelephone: String,
    val adress: String,
    val numberroom2: String,
    val numberroom3: String
)