package com.example.myapplication.homescreen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.presentation.common.ContainerBorder
import com.example.myapplication.presentation.common.Containerdemo
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.presentation.common.subcorcontainer
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun PetProfileScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        content = {
            paddingValues -> PetProfileform(modifier = Modifier.padding(paddingValues))

        }

    )

}
@Composable
fun PetProfileform(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDFF8D8))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp, vertical = 16.dp)
    ) {
        // Header với ảnh mèo và tên thú cưng
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Phần hiển thị thông tin dinh dưỡng
        NutritionInfoSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Phần thực đơn
        DailyMenuSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Thực phẩm sắp hết hạn
        ExpiringFoodSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Khám phá sản phẩm
        DiscoverProductsSection()

        //Demo product
        DemoProduct()


        Spacer(modifier = Modifier.height(100.dp))

    }
}




@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                ,
            painter = painterResource(id = R.drawable.tiktok),
            contentDescription = "Picture Avatar",
            contentScale = ContentScale.Crop
        )

        Text(" Cun Pho Mai Que", modifier = Modifier.padding(10.dp))
        Text("Be trai")

    }
}

@Composable
fun NutritionInfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 50.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 50.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .padding(8.dp)
    ) {
        // Top Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Status of Pet",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.pet_care),
                    contentDescription = "Picture Avatar",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your Pet overweight 1kg",
                    fontSize = 12.sp,
                    color = Color.Red,
                    fontStyle = FontStyle.Italic
                )
            }

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        Color.White,
                        shape = CircleShape
                    )
                    .border(
                        width = 4.dp,
                        color = Color(0xFF469E67).copy(alpha = 0.5f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "0",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text(text = "Sum 800 g")
                }
            }
        }

        // Divider
        Divider(
            color = Color.Gray.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Bottom Section: Nutritional Details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Calories Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calories), // Replace with your drawable resource
                    contentDescription = "Calories",
                    modifier = Modifier.size(14.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Calories", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "0/508g", fontSize = 12.sp)
            }

            // Protein Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.steak), // Replace with your drawable resource
                    contentDescription = "Protein",
                    modifier = Modifier.size(14.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Protein", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "0/298g", fontSize = 12.sp)
            }

            // Fat Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fat), // Replace with your drawable resource
                    contentDescription = "Fat",
                    modifier = Modifier.size(14.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Fat", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "0/298g", fontSize = 12.sp)
            }
        }
    }
}



@Composable
fun DailyMenuSection() {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .padding(0.dp,20.dp,0.dp,0.dp)
        .fillMaxWidth()
        .horizontalScroll(scrollState)


    ){
        ContainerBorder(
            textTitle = "Bữa sáng",
            textTime = "6:00",
            text = "Bữa ăn chiếm 15% lượng Kcal / ngày",
            image = R.drawable.pet_food, // Replace with the correct image resource
            colorTransform1 = Color(0xFFFA8173), // Replace with your desired colors
            colorTransform2 = Color(0xFFFEA885)
        )
        ContainerBorder(
            textTitle = "Bữa sáng",
            textTime = "6:00",
            text = "Bữa ăn chiếm 15% lượng Kcal / ngày",
            image = R.drawable.pet_food, // Replace with the correct image resource
            colorTransform1 = Color(0xFFFBBF64), // Replace with your desired colors
            colorTransform2 = Color(0xFFF9D37D)
        )
        ContainerBorder(
            textTitle = "Bữa sáng",
            textTime = "6:00",
            text = "Bữa ăn chiếm 15% lượng Kcal / ngày",
            image = R.drawable.pet_food, // Replace with the correct image resource
            colorTransform1 = Color(0xFFB79CFD), // Replace with your desired colors
            colorTransform2 = Color(0xFF9889FC)
        )
    }


}

@Composable
fun ExpiringFoodSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 50.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 50.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .padding(8.dp)
    ) {
        // Top Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.catin),
                    contentDescription = "Picture Avatar",
                    contentScale = ContentScale.Crop
                )
            }

            Box(

            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(text = "Opps !!! You dont have any product in the store",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF4F9F6C)
                    )
                    NewsTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Add now!",
                        onClick = {

                        })

                }
            }
        }

    }
}


@Composable
fun DiscoverProductsSection() {
 Row(
    Modifier.fillMaxWidth()
 ) {
     subcorcontainer()
 }

}
@Composable
fun DemoProduct() {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .padding(0.dp,20.dp,0.dp,0.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ){
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
        Containerdemo(
            text1 = "Vitamin E",
            text2 = "BO sung canxi" ,
            painter = painterResource(id = R.drawable.pet_food_bro)
        )
    }
}
@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = {
                Text(
                    "Home",
                fontSize = 10.sp) },
            selected = true,
            onClick = {

            },
            selectedContentColor = Color(0xFF469E67),
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Place, contentDescription = null) },
            label = { Text("Thú y",
                fontSize = 10.sp) },
            selected = false,
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Store", fontSize = 10.sp) },
            selected = false,
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Diet", fontSize = 10.sp) },
            selected = false,
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
            label = { Text("Reserve", fontSize = 10.sp) },
            selected = false,
            onClick = {

            }
        )
    }
}

@Preview
@Composable
fun ViewhomeScreen(){
    MyApplicationTheme {
        PetProfileScreen()
    }
}