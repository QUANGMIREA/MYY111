package com.example.myapplication.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.presentation.common.ContainerBorder
import com.example.myapplication.presentation.common.Containerdemo
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.presentation.common.subcorcontainer
import com.example.myapplication.ui.theme.MyApplicationTheme
import StoreScreen
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.LoginScreen.getUserId
import com.example.myapplication.Model.CartState
import com.example.myapplication.homescreen.bag.bagscreen
import com.example.myapplication.homescreen.cart.CartScreen


@Composable
fun PetProfileScreen(petProfileViewModel: PetProfileViewModel,cartViewModel: CartViewModel, navController: NavController) {
    val currentScreen = remember { mutableStateOf<Screen>(Screen.Home) }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    val userId = getUserId(context)


    Scaffold(
        bottomBar = { BottomNavigationBar(currentScreen) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)
                //.fillMaxSize().padding(bottom = 40.dp)
            )
            {
                when (currentScreen.value) {
                    is Screen.Home -> PetProfileform(viewModel =petProfileViewModel)
                    is Screen.Store -> StoreScreen(viewModel = petProfileViewModel,currentScreen)
                    is Screen.Cart -> CartScreen(viewModel = cartViewModel, userId ,currentScreen = currentScreen, navController = navController)
                    is Screen.Bag -> bagscreen(viewModel = petProfileViewModel,currentScreen)
                }
            }
        }
    )
}


@Composable
fun PetProfileform(modifier: Modifier = Modifier,viewModel: PetProfileViewModel){
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
        DemoProduct(viewModel = viewModel)


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
fun DemoProduct(viewModel: PetProfileViewModel) {
    val scrollState = rememberScrollState()
    val products = viewModel.filteredProducts.observeAsState(initial = emptyList()).value
    // Tạo trạng thái để lưu sản phẩm được chọn
    //val dogProducts = products.filter { product -> product.type == "2" }
    // Kiểm tra nếu có sản phẩm loại "dog" thì hiển thị, nếu không thì thông báo không có sản phẩm
//    if (dogProducts.isNotEmpty()) {
//        Row(
//            modifier = Modifier
//                .padding(0.dp, 20.dp, 0.dp, 0.dp)
//                .fillMaxWidth()
//                .horizontalScroll(scrollState),
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            // Hiển thị từng sản phẩm loại "dog"
//            dogProducts.forEach { product ->
//                Containerdemo(
//                    text1 = product.tensanpham,
//                    text2 = product.motasanpham,
//                    text3 = product.giasp,
//                    painter = rememberImagePainter(data = product.hinhanh) // Hiển thị ảnh sản phẩm
//                )
//            }
//        }
//    } else {
//        // Nếu không có sản phẩm loại "dog"
//        Text("No Dog Products available")
//    }
//}

    if (products != null && products.isNotEmpty()) {
    Row(
        modifier = Modifier
            .padding(0.dp,20.dp,0.dp,0.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ){
        products.forEach { product ->
            Containerdemo(
                text1 = product.tensanpham,
                text2 = product.motasanpham,
                text3 = product.giasp,
                painter = rememberImagePainter(data = product.hinhanh) // Replace with actual image
        ,onItemClicked = {
                    // Hành động khi nhấn vào sản phẩm (nếu cần)
                    // Bạn có thể để trống nếu không có hành động cụ thể
                }
            )
        }

    }}
        else{
            Text("No Product available")
        }
}
@Composable
fun BottomNavigationBar(currentScreen: MutableState<Screen>) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray,
       // modifier = Modifier.offset(y = (-35).dp) // Di chuyển BottomNavigationBar lên 4dp

    ) {
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.store), // Thay thế bằng ảnh của bạn
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước nếu cần
                )
            },
            label = {
                Text(
                    "Home",
                    fontSize = 10.sp
                )
            },
            selected = currentScreen.value is Screen.Home,
            onClick = {
                currentScreen.value = Screen.Home
            },
            selectedContentColor = Color(0xFF469E67),
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.shopping_cart), // Thay thế bằng ảnh của bạn
                    contentDescription = "Cart Icon",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước nếu cần
                )
            },
            label = { Text("Cart",
                fontSize = 10.sp) },
            selected = false,
            onClick = {
                currentScreen.value = Screen.Cart
            }
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.grocery_store), // Thay thế bằng ảnh của bạn
                    contentDescription = "Grocery Icon",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước nếu cần
                )
            },
            label = { Text("Store", fontSize = 10.sp) },
            selected = currentScreen.value is Screen.Store,
            onClick = { currentScreen.value = Screen.Store },
            selectedContentColor = Color(0xFF469E67),
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.diet), // Thay thế bằng ảnh của bạn
                    contentDescription = "Diet Icon",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước nếu cần
                )
            },
            label = { Text("Diet", fontSize = 10.sp) },
            selected = currentScreen.value is Screen.Bag,
            onClick = {
            currentScreen.value = Screen.Bag
            },
            selectedContentColor = Color(0xFF469E67),
            unselectedContentColor = Color.Gray
        )
        BottomNavigationItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.school_bag), // Thay thế bằng ảnh của bạn
                    contentDescription = "BackPack Icon",
                    modifier = Modifier.size(24.dp) // Điều chỉnh kích thước nếu cần
                )
            },
            label = { Text("Reserve", fontSize = 10.sp) },
            selected = false,
            onClick = {

            },
            selectedContentColor = Color(0xFF469E67),
            unselectedContentColor = Color.Gray
        )
    }
}

@Preview
@Composable
fun ViewhomeScreen(){

    val petProfileViewModel: PetProfileViewModel = viewModel() // Khởi tạo ViewModel
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    MyApplicationTheme {

        PetProfileScreen( petProfileViewModel = petProfileViewModel,cartViewModel = cartViewModel,navController )
    }
}