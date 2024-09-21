package com.example.myapplication.LoginScreen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Model.LoginResponse
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.presentation.onboarding.Dimens.MediumPadding2

import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Register(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Thêm ảnh nền chìm
        Image(
            painter = painterResource(id = R.drawable.geng),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(400.dp).align(Alignment.Center).alpha(0.5f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Register",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF469E67)
            )


            // Username TextField
            OutlinedTextField(
                value = fullname,
                onValueChange = { newValue -> fullname = newValue },
                label = { Text("Fullname") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { newValue -> email = newValue },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            )

            // Username TextField
            OutlinedTextField(
                value = username,
                onValueChange = { newValue -> username = newValue },
                label = { Text("Username") },
                leadingIcon = { Icon(Icons.Default.AccountBox, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            )



            OutlinedTextField(
                value = password,
                onValueChange = { newValue -> password = newValue },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            )



            OutlinedTextField(
                value = confirmpassword,
                onValueChange = { confirmpassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )




            NewsTextButton(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = "Sign up",
                onClick = {

                    registerUser(context, fullname, email, username, password, navController)
                    navController.navigate("Addprofile")
                }
            )




            Text(text = "Or register with:")


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = painterResource(
                    id = R.drawable.tiktok
                ),
                    contentDescription = "Icon",
                    modifier = Modifier.size(60.dp).clickable { }
                )
                Image(painter = painterResource(
                    id = R.drawable.facebook
                ),
                    contentDescription = "Icon",
                    modifier = Modifier.size(60.dp).clickable { }
                )

            }
        }
    }
}
fun registerUser(context: android.content.Context, fullname: String, email: String, username: String, password : String, navController: NavController) {
    // Gọi API bằng Retrofit
    RetrofitClient.instance.registerUser(fullname, email, username, password)
        .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                if (loginResponse != null && loginResponse.success == true) {
                    // Đăng ki thành công
                    Toast.makeText(context, loginResponse.message, Toast.LENGTH_SHORT).show()
                    // Điều hướng sang màn hình khác
                    //navController.navigate("HomeScreen")
                } else {
                    // Đăng ki thất bại
                    Toast.makeText(context, loginResponse?.message , Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Lỗi kết nối: " + t.message, Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }
        })
}

@Preview(showBackground = true)
@Composable
fun Registerreview(){
    MyApplicationTheme(){
        val navController = rememberNavController()
        Register(navController = navController)
    }
}
