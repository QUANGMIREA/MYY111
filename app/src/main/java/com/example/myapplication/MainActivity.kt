package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.LoginScreen.Login
import com.example.myapplication.LoginScreen.Register
import com.example.myapplication.addProfile.AddPetProfileScreen
import com.example.myapplication.addProfile.CameraScreen
import com.example.myapplication.addProfile.PetInfoScreen
import com.example.myapplication.homescreen.CartViewModel
import com.example.myapplication.homescreen.PetProfileScreen
import com.example.myapplication.homescreen.PetProfileViewModel
import com.example.myapplication.homescreen.Screen
import com.example.myapplication.homescreen.cart.CartScreen
import com.example.myapplication.homescreen.cart.paycartscreen
import com.example.myapplication.presentation.onboarding.OnBoardingScreen
import com.example.myapplication.presentation.onboarding.components.OnBoardingPage
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Box(modifier = Modifier.background(color = Color.White)){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Login", builder = {
                        composable("Login"){
                            Login(navController)
                        }
                        composable("OnBoardingScreen"){
                            OnBoardingScreen(navController)
                        }
                        composable("Register"){
                            Register(navController)
                        }
                        composable("Addprofile"){
                            AddPetProfileScreen(navController)
                        }
                        composable("Infopet"){
                            PetInfoScreen(navController)
                        }
                        composable("Opencamera"){
                            CameraScreen(isPreview = false,navController)
                        }
                        composable("HomeScreen") {
                            val petProfileViewModel: PetProfileViewModel = viewModel() // Khởi tạo PetProfileViewModel
                            val cartViewModel: CartViewModel = viewModel()
                            PetProfileScreen( petProfileViewModel = petProfileViewModel,
                                cartViewModel = cartViewModel,
                                navController = navController) // Truyền NavController và ViewModel vào PetProfileScreen
                        }
                        composable("storescreen") {

                        }
                        composable("CartScreen") {
                            paycartscreen(navController)
                        }


                    })
                }
                }
            }
        }
    }


