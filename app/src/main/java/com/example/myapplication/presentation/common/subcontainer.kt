package com.example.myapplication.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.homescreen.PetProfileScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ContainerBorder(
    textTitle: String,
    textTime: String,
    text: String,
    image: Int, // Resource ID for image
    colorTransform1: Color,
    colorTransform2: Color
) {
    Box(
        modifier = Modifier.width(150.dp)
    ) {
        Column {
            // Background Box with gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 15.dp, 0.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(colorTransform1, colorTransform2),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 100.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
            ) {
                // Content inside the background box
                Column(
                    modifier = Modifier.fillMaxWidth().padding(5.dp, 45.dp, 10.dp, 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = textTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(0.dp, 5.dp),
                        text = textTime,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        text = text,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        maxLines = 2
                    )
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                        ,
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = 0.5f,
                    )

                }
            }
        }
        // Image at the top corner
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .offset(x = 5.dp, y = (-20).dp)
        )
    }
}

@Preview
@Composable
fun ViewContainer() {
    MyApplicationTheme {
        ContainerBorder(
            textTitle = "Bữa sáng",
            textTime = "6:00",
            text = "Bữa ăn chiếm 15% lượng Kcal / ngày",
            image = R.drawable.pet_food, // Replace with the correct image resource
            colorTransform1 = Color.Red, // Replace with your desired colors
            colorTransform2 = Color.Yellow
        )
    }
}