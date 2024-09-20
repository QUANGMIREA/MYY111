package com.example.myapplication.LoginScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
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
fun Login(navController: NavController){
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(
            id = R.drawable.img_box_cat),
            contentDescription = "Icon",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))


        Text(text = "Welcome to petshop", fontSize = 28.sp, fontWeight =  FontWeight.Bold, color = Color(0xFF469E67))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Please login", fontSize = 14.sp, fontStyle = FontStyle.Italic, color = Color(0xFF20C056))
        // Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {

                }
            ) {
                Text(text = "You forgot password?", fontStyle = FontStyle.Italic)
            }
        }


        NewsTextButton(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = "Login",
            onClick = {
                loginUser(context, username, password, navController)

            },

        )

        Spacer(modifier = Modifier.height(5.dp))

        TextButton(onClick = {
            navController.navigate("Register")
        }) { Text(text="You still dont have account ? Let's sign in", fontStyle = FontStyle.Italic) }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Or sign in with:")
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
             ) {
            Image(painter = painterResource(
                id = R.drawable.tiktok),
                contentDescription = "Icon",
                modifier = Modifier.size(60.dp).clickable {  }
            )
            Image(painter = painterResource(
                id = R.drawable.facebook),
            contentDescription = "Icon",
            modifier = Modifier.size(60.dp).clickable {  }
            )

        }
    }
}

fun loginUser(context: android.content.Context, username: String, password: String, navController: NavController) {
    // Gọi API bằng Retrofit
    RetrofitClient.instance.loginUser(username, password)
        .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                if (loginResponse != null && loginResponse.success == true) {
                    // Đăng nhập thành công
                    Toast.makeText(context, loginResponse.message, Toast.LENGTH_SHORT).show()
                    // Điều hướng sang màn hình khác
                    //navController.navigate("HomeScreen")
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(context, loginResponse?.message ?: "Lỗi đăng nhập", Toast.LENGTH_SHORT).show()
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
fun Loginreview(){
    MyApplicationTheme(){
        val navController = rememberNavController()
        Login(navController = navController)
    }
}
