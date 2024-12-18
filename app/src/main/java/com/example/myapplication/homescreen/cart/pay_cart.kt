package com.example.myapplication.homescreen.cart

import android.content.Context
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Model.CartState
import com.example.myapplication.Model.LoginResponse
import com.example.myapplication.R
import com.example.myapplication.addProfile.pushinfoPet
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.homescreen.Screen
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun paycartscreen(navController: NavController){
    var nameuser by remember { mutableStateOf("") }
    var numbertelephone by remember { mutableStateOf("") }
    var adress by remember { mutableStateOf("") }
    var numberroom by remember { mutableStateOf("") }
    var numberroom2 by remember { mutableStateOf("") }
    var numberroom3 by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(color =  Color(0xFFCAF4FF)
            )
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ){
            IconButton(
                onClick = {

                },
                modifier = Modifier.size(50.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )


            }
            Text(

                text = "Вашa корзина покупок",
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(

            text = "Информация о покупателе",
            modifier = Modifier.padding(start = 15.dp).fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier= Modifier.fillMaxSize().padding(start = 15.dp,end= 10.dp, bottom = 10.dp)
        ) {
            Feild(text = "Имя",
                value = nameuser,
                onValueChange = {
                        newValue ->
                    nameuser = newValue
                },modifier = Modifier)
            Feild(text = "Номер телефона",
                value = numbertelephone,
                onValueChange = {
                        newValue ->
                    numbertelephone = newValue
                },modifier = Modifier)
            Feild(text = "Адрес",
                value = adress,
                onValueChange = {
                        newValue ->
                    adress = newValue
                },
                modifier = Modifier)


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Feild(text = "Номер комнаты",
                    value = numberroom2,
                    onValueChange = {
                            newValue ->
                        numberroom2 = newValue
                    },
                    modifier = Modifier.weight(1f).padding(end = 8.dp))

                Feild(text =  " Номер комнаты",
                    value = numberroom3,
                    onValueChange = {
                            newValue ->
                        numberroom3 = newValue
                    },
                    modifier = Modifier.weight(1f).padding(start = 8.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            SummarySection(cartState = CartState(),
                internalPadding = 0.dp)
            Spacer(modifier = Modifier.height(10.dp))

            val context = LocalContext.current

            NewsTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Confirm",
                onClick = {
                    val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
                    val userId = sharedPreferences.getInt("user_id", -1)

                    if (userId != -1) {
                        pushinfoorder(context, userId, nameuser, numbertelephone, adress, numberroom2, numberroom3)
                    } else {
                        Toast.makeText(context, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show()
                    }
                }
            )

        }


    }
}

fun pushinfoorder(context: android.content.Context,user_id: Int, nameuser: String, numbertelephone: String, adress: String, numberroom2: String, numberroom3:String){
    RetrofitClient.instance.pushinfoorder(user_id,nameuser, numbertelephone, adress,numberroom2,numberroom3)
        .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                if (loginResponse != null && loginResponse.success == true) {

                    Toast.makeText(context, loginResponse.message, Toast.LENGTH_SHORT).show()

                    //navController?.navigate("HomeScreen")
                } else {

                    Toast.makeText(context, loginResponse?.message , Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Fail Connect: " + t.message, Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }
        })
}


@Preview
@Composable
fun Reviewpaycartscreen(){
    MyApplicationTheme {
        val navController = rememberNavController()
        paycartscreen(navController = navController)
    }
}
