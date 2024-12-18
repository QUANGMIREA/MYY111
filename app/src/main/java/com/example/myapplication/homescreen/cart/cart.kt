package com.example.myapplication.homescreen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myapplication.Model.CartItem
import com.example.myapplication.Model.CartState
import com.example.myapplication.R
import com.example.myapplication.homescreen.CartViewModel
import com.example.myapplication.homescreen.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun CartScreen(viewModel: CartViewModel,userId: Int, currentScreen: MutableState<Screen>,navController: NavController){
    val cartItems = viewModel.cartItems.observeAsState(emptyList())
    val cartState = viewModel.cartState.observeAsState(CartState())
    // Tính tổng giá trị giỏ hàng
    val total = cartItems.value.sumOf { it.total }
    val tax = total * 0.1 // Ví dụ: tính thuế 10%
    val deliveryFee = 50.0
    val discount = 0.0 // Giảm giá (nếu có)
    val finalTotal = total + tax + deliveryFee - discount

    // Tạo đối tượng `CartState` với các giá trị tính toán
    val currentCartState = CartState(
        total = total,
        tax = tax,
        delivery_fee = deliveryFee,
        discount = discount,
        final_total = finalTotal
    )
    // Fetch cart items when the composable is loaded
    LaunchedEffect(userId) {
        viewModel.fetchCartItems(userId)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color =  Color(0xFFCAF4FF)
            )
    )
    {
        Spacer(modifier = Modifier.height(10.dp))
        Text(

            text = "Вашa корзина покупок",
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ){
            cartItems.value.forEachIndexed{ index,item ->
                CartItemView(item){
                    newQuantity ->
                    viewModel.updateCartItemQuantity(userId,item.product_id,newQuantity)
                }
                if (index < cartItems.value.size - 1) {
                    Spacer(modifier = Modifier.height(16.dp)) // Adjust the padding as needed
                }
            }
        }
        // Phần mã giảm giá
        PromoCodeSection()

        // Phần tổng kết đơn hàng
        SummarySection(currentCartState, internalPadding = 16.dp)

        // Nút thanh toán
        PayButton(navController = navController)
    }

}
@Composable
fun CartItemView(item: CartItem, onQuantityChange:(Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp,
                    bottomStart = 15.dp
                )
            )
            .border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp,
                    bottomStart = 15.dp
                )
            )
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberImagePainter(item.hinhanh),
                contentDescription = item.product_name,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.product_name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Цена: ${item.price} рублей")
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    if(item.quantity>1)
                    {
                        onQuantityChange(item.quantity-1)
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.left_arrow), // Replace with your image resource
                        contentDescription = "Giảm số lượng",
                        modifier = Modifier.size(24.dp) // Adjust size as needed
                    )
                }
                Text(text = "${item.quantity}")
                IconButton(onClick = {
                    onQuantityChange(item.quantity+1)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.right_arrow), // Replace with your image resource
                        contentDescription = "Tăng số lượng",
                        modifier = Modifier.size(24.dp) // Adjust size as needed
                    )
                }
            }


        }
    }


}

@Composable
fun PromoCodeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50)
            )
            .border(
                width = 1.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = { /* handle promo code input */ },
            label = { Text("Промо-код") },
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {

            },
            shape = RoundedCornerShape(50), // Set shape directly here
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF4A90E2),
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(48.dp)
                .padding(end=4.dp)// Adjust height if needed to match TextField
        ) {
            Text("Применять")
        }
    }
}


@Composable
fun SummarySection(cartState: CartState,internalPadding: Dp = 16.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(internalPadding)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp,
                    bottomStart = 15.dp
                )
            )
            .border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp,
                    bottomStart = 15.dp
                )
            )
        

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SummaryRow("Всего в корзине:", "${cartState.total} ₽")
            SummaryRow("Налог:", "${cartState.tax} ₽")
            SummaryRow("Доставка:", "${cartState.delivery_fee} ₽")
            SummaryRow("Промо-скидка:", "-${cartState.discount} ₽")
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            SummaryRow("Промежуточный итог:", "${cartState.total + cartState.tax + cartState.delivery_fee - cartState.discount} ₽")
        }
    }

}

@Composable
fun SummaryRow(label: String, amount: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = MaterialTheme.typography.body2)
        Text(text = amount, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun PayButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("CartScreen")

        },
        shape = RoundedCornerShape(50), // Set shape directly here
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4A90E2),
            contentColor = Color.White
        ),
        modifier = Modifier
            .height(48.dp).fillMaxWidth().padding(start = 16.dp,end= 16.dp) // Adjust height if needed to match TextField
    ) {
        Text("Pay")
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview
@Composable
fun PreviewCartScreen() {
    MyApplicationTheme {
        val viewModel = CartViewModel()
        val currentScreen = remember { mutableStateOf<Screen>(Screen.Cart) }
        val userId = 15
        val navController = rememberNavController()

        CartScreen(viewModel = viewModel, userId = userId, currentScreen= currentScreen, navController = navController)
    }
}
