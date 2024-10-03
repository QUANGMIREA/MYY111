package com.example.myapplication.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.w3c.dom.Text

@Composable
fun Containerdemo(
    text1: String,
    text2: String,
    painter: Painter

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )

            .border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .padding(8.dp)
    ) {
        // Box to contain the arc, image, and text
        Box(
            modifier = Modifier.fillMaxSize().padding(5.dp),
            contentAlignment = Alignment.Center
        ) {


            // Product Image
            Image(
                painter = painter, // Replace with your image resource
                contentDescription = "Product Image",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )

            // Text below image
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 160.dp)
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = text1,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFF4F9F6C),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold

                    )
                )
                Text(
                    text = text2,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,


                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun ViewContainer2() {
    MyApplicationTheme {
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
    }
}