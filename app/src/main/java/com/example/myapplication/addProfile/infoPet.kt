package com.example.myapplication.addProfile


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.LoginScreen.loginUser
import com.example.myapplication.Model.LoginResponse
import com.example.myapplication.R
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun PetInfoScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Pet Information",
                        modifier = Modifier.align(Alignment.Center),
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF469E67),
                        fontSize = 16.sp

                    )
                }
                        },
                backgroundColor = Color.White,
                modifier = Modifier
            )
        },
        content = { paddingValues ->
            PetInfoForm(modifier = Modifier.padding(paddingValues))
        }
    )



}

@Composable
fun PetInfoForm(modifier: Modifier = Modifier) {

    val options = listOf("Dry", "Warm", "Semi-warm", "Home cooked", "Fresh")

    var namepet by remember { mutableStateOf("") }
    var typeofpet by remember { mutableStateOf("") }
    var dateofbirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }



    val selectedOptions = remember { mutableStateOf(setOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {

        }) {
            Image(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.tiktok),
                contentDescription = "Picture Avatar",
                contentScale = ContentScale.Crop
            )



        }
        Column(
        modifier= Modifier.fillMaxWidth()

        ) {
            Text(text = "Name pet",
                modifier = Modifier,
                fontWeight = FontWeight.Bold
                )

            OutlinedTextField(
                value = namepet,
                onValueChange = {namepet = it},
                label = { Text(text = "") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier= Modifier.fillMaxWidth()

        ) {
            Text(text = "Type of pet",
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = typeofpet,
                onValueChange = {typeofpet = it},
                label = { Text(text = "") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier= Modifier.fillMaxWidth()

        ) {
            Text(text = "Date of Birth",
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = dateofbirth,
                onValueChange = {dateofbirth = it},
                label = { Text(text = "") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier= Modifier.fillMaxWidth()

        ) {
            Text(text = "Weight",
                modifier = Modifier,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = weight,
                onValueChange = {weight = it},
                label = { Text(text = "") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        var selectedgender by remember { mutableStateOf("Male") }

        Column(

        ) {
            Text(text = "Sex: ",
                fontWeight = FontWeight.Bold
                )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = true, onClick = {
                        selectedgender = "Male"
                    })
                    Text(text = "Male",fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically
                    ) {
                    RadioButton(selected = false, onClick = {
                        selectedgender = "Female"
                    })
                    Text(text = "Female",fontWeight = FontWeight.Bold)
                }
            }
        }
        Column {
            Text(text = "Nutrition",
                fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                options.forEach { option ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .toggleable(
                                value = selectedOptions.value.contains(option),
                                onValueChange = {
                                    selectedOptions.value = if (it) {
                                        selectedOptions.value + option
                                    } else {
                                        selectedOptions.value - option
                                    }
                                }
                            )
                    ) {
                        Checkbox(
                            checked = selectedOptions.value.contains(option),
                            onCheckedChange = null
                        )
                        Text(text = option)
                    }
                }
            }
        }
        val context = LocalContext.current

        NewsTextButton(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = "Save Data",
            onClick = {
                pushinfoPet(context ,namepet, typeofpet ,dateofbirth ,weight ,selectedgender, selectedOptions.value.joinToString(", ") )

            },

            )

    }
}
fun pushinfoPet(context: android.content.Context, namepet: String, typeofpet: String, dateofbirth: String, weight: String, selectgender:String, nutrition: String){
    RetrofitClient.instance.pushinfoPet(namepet, typeofpet, dateofbirth,weight,selectgender,nutrition)
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
fun ViewinfoPet(){
    MyApplicationTheme {
        val navController = rememberNavController()
        PetInfoScreen(navController = navController)
    }
}