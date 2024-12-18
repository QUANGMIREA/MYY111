package com.example.myapplication.homescreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Store : Screen("store")
    object Cart : Screen("cart")
    object Bag: Screen("bag")
}
