package com.example.myapplication.Model

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val result: List<User>?

)


data class User(
    val user_id: Int,
    val username: String,
    val telephone: String,
    val address: String,
    val email : String,
    val password: String
)