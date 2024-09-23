package com.example.myapplication.addProfile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.myapplication.R


import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AddPetProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceEvenly

    ) {
        // Image at the top
        Image(
            painter = painterResource(id = R.drawable.img_box_cat),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight(0.5f)


        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight().padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                textAlign = TextAlign.Center,
                text = "Add profile for pet",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color =  Color(0xFF469E67)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Start a new journey with pets with the LHQ ",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                color =  Color(0xFF469E67)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("Opencamera")
                        },
                        modifier = Modifier
                            .size(120.dp)
                            .background( Color(0xFFDADADA), shape = RoundedCornerShape(10.dp))
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.img_cam),
                            contentDescription = "Scan Pet/Scan QR",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )

                    }

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Scan Pet/ Scan QR",
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF469E67)
                    )

                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()

                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("InfoPet")
                        },
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFF469E67), shape = RoundedCornerShape(10.dp))
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.img_add),
                            contentDescription = "Scan Pet/Scan QR",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )


                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Add profile pet",
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF469E67)
                    )

                }


            }

        }


    }


}

@Preview(showBackground = true, apiLevel = 29)
@Composable
fun Addprofilereview(){
    MyApplicationTheme(){
        val navController = rememberNavController()
        AddPetProfileScreen(navController = navController)
    }
}
