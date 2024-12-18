package com.example.myapplication.presentation.common
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun Containerdemo(
    text1: String,
    text2: String,
    text3: String,
    painter: androidx.compose.ui.graphics.painter.Painter,
    onItemClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(120.dp)
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
                shape = RoundedCornerShape(20.dp)
            )
            .padding(10.dp, 10.dp)
            .clickable { onItemClicked() }
    ) {
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = text1,
            fontSize = 12.sp,
            color = Color(0xFF4F9F6C),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = text2,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = text3,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewContainer2(
    text1: String,
    text2: String,
    text3: String,
    painter: androidx.compose.ui.graphics.painter.Painter
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheetLayout(
            sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded),
            sheetContent = {
                BottomSheetContent(
                    painter = painter,
                    text1 = text1,
                    text2 = text2,
                    text3 = text3
                )
            },
            content = {
                Containerdemo(
                    text1 = text1,
                    text2 = text2,
                    text3 = text3,
                    painter = painter
                ) {
                    showBottomSheet = true
                }
            }
        )
    } else {
        Containerdemo(
            text1 = text1,
            text2 = text2,
            text3 = text3,
            painter = painter
        ) {
            showBottomSheet = true
        }
    }
}

@Composable
fun BottomSheetContent(painter: androidx.compose.ui.graphics.painter.Painter, text1: String, text2: String, text3: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = "Product Image",
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text1,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text2,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text3,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewContainerDemo() {
    ViewContainer2(
        text1 = "Vitamin E",
        text2 = "BO sung canxi",
        text3 = "10000 đ",
        painter = painterResource(id = R.drawable.pet_food_bro)
    )
}
