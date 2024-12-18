package com.example.myapplication.homescreen.bag

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.Model.Product
import com.example.myapplication.homescreen.PetProfileViewModel
import com.example.myapplication.homescreen.Screen
import com.example.myapplication.homescreen.store.TopRoundedShape
import com.example.myapplication.homescreen.store.searchbarStore
import com.example.myapplication.presentation.common.NewsTextButton
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import uploadCartToServer

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun bagscreen(viewModel: PetProfileViewModel, currentScreen: MutableState<Screen>){
    val context = LocalContext.current

    val cartItems = remember { mutableStateListOf<Product>() } // Danh sách sản phẩm trong giỏ hàng
    val selectedProduct = remember { mutableStateOf<Product?>(null) } // Sản phẩm được chọn để hiển thị BottomSheet
    val productBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden) // BottomSheet cho chi tiết sản phẩm
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }
    val cartCount = cartItems.size

    val handleProductClick: (Product) -> Unit = { product ->
        selectedProduct.value = product
        coroutineScope.launch {
            productBottomSheetState.show()
        }
    }
    ModalBottomSheetLayout(
        sheetState = productBottomSheetState,
        sheetContent = {
            selectedProduct.value?.let { product ->
                // Nội dung hiển thị sản phẩm đã chọn trong BottomSheet
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(TopRoundedShape(radius = 16f))
                        .background(MaterialTheme.colors.surface)
                )
                {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberImagePainter(data = product.hinhanh),
                            contentDescription = "Product Image",
                            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = product.tensanpham, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(text = product.motasanpham)
                        Text(text = "Giá: ${product.giasp} VND")
                        Spacer(modifier = Modifier.height(16.dp))
                        var isUploading = remember { mutableStateOf(false) }

                        NewsTextButton(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = "Confirm !",
                            onClick = {
                                cartItems.add(product) // Thêm sản phẩm vào giỏ hàng
                                val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
                                val userId = sharedPreferences.getInt("user_id", -1) // Nếu không có user_id, trả về -1
                                println(userId)
                                if (userId != -1) {
                                    // Gọi hàm uploadCartToServer để gửi giỏ hàng
                                    uploadCartToServer(context,  cartItems.toList())
                                    isUploading.value = false // Reset trạng thái sau khi hoàn thành


                                } else {
                                    Toast.makeText(context, "User ID không hợp lệ", Toast.LENGTH_SHORT).show()
                                    isUploading.value = false

                                }
                                coroutineScope.launch { productBottomSheetState.hide() } // Đóng BottomSheet sau khi thêm sản phẩm
                            }
                        )

                    }


                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color =  Color(0xFFCAF4FF)
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            searchbarStore(
                viewModel = viewModel,
                cartCount = cartCount,
                Onclick = {
                    // Xử lý khi nhấn vào giỏ hàng (ví dụ: chuyển đến màn hình giỏ hàng)
                    Toast.makeText(context, "Giỏ hàng clicked!", Toast.LENGTH_SHORT).show()
                    // navController.navigate("cart_screen")
                },
                onProductClick = handleProductClick
            )



        }
    }
}

@Preview
@Composable
fun PreviewBagScreen() {
    MyApplicationTheme {
        val viewModel: PetProfileViewModel = viewModel()
        val currentScreen = remember { mutableStateOf<Screen>(Screen.Bag) } // Thêm currentScreen

        bagscreen(viewModel = viewModel, currentScreen = currentScreen)
    }
}