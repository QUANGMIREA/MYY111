import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.myapplication.Model.LoginResponse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.Model.Product
import com.example.myapplication.R
import com.example.myapplication.homescreen.PetProfileViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.LoginScreen.getUserId
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.homescreen.BottomNavigationBar
import com.example.myapplication.homescreen.Screen
import com.example.myapplication.homescreen.store.TopRoundedShape
import com.example.myapplication.homescreen.store.searchbarStore
import com.example.myapplication.presentation.common.Containerdemo
import com.example.myapplication.presentation.common.NewsTextButton


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StoreScreen(viewModel: PetProfileViewModel, currentScreen: MutableState<Screen>) {

    val context = LocalContext.current

    val cartItems = remember { mutableStateListOf<Product>() } // Danh sách sản phẩm trong giỏ hàng
    val selectedProduct = remember { mutableStateOf<Product?>(null) } // Sản phẩm được chọn để hiển thị BottomSheet
    val productBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden) // BottomSheet cho chi tiết sản phẩm
    val coroutineScope = rememberCoroutineScope()

    val filteredProducts by viewModel.filteredProducts.observeAsState(emptyList())
    val cartCount = cartItems.size

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }
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


                            // Hiển thị danh sách các mục được đề xuất
                            ProductSection(viewModel = viewModel, text4 = "Đề xuất thực phẩm", onItemClicked = { product ->
                                selectedProduct.value = product
                                coroutineScope.launch {
                                    productBottomSheetState.show() // Hiển thị chi tiết sản phẩm trong BottomSheet
                                }
                            })

                            // Hiển thị danh sách thực phẩm có sẵn
                            ProductSection(viewModel = viewModel, text4 = "Thực phẩm có sẵn", onItemClicked = { product ->
                                selectedProduct.value = product
                                coroutineScope.launch {
                                    productBottomSheetState.show() // Hiển thị chi tiết sản phẩm
                                }
                            })

                            // Hiển thị danh sách thuốc và vitamin
                            ProductSection(viewModel = viewModel, text4 = "Thuốc và vitamin có sẵn", onItemClicked = { product ->
                                selectedProduct.value = product
                                coroutineScope.launch {
                                    productBottomSheetState.show() // Hiển thị chi tiết sản phẩm
                                }
                            })
                        }
                    }
                }







@Composable
fun ProductSection(
    viewModel: PetProfileViewModel,
    text4: String,
    onItemClicked: (Product) -> Unit // Truyền hàm callback onItemClicked để kiểm soát BottomSheet
) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        val scrollState = rememberScrollState()
        val products = viewModel.filteredProducts.observeAsState(initial = emptyList()).value
        val dogProducts = products.filter { product -> product.type == "1" } // Lọc sản phẩm theo loại '1'

        Text(
            text = text4,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
            ),
        )

        if (dogProducts.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                dogProducts.forEach { product ->
                    Containerdemo(
                        text1 = product.tensanpham,   // Tên sản phẩm
                        text2 = product.hansudung,    // Hạn sử dụng
                        text3 = product.giasp,        // Giá sản phẩm
                        painter = rememberImagePainter(data = product.hinhanh), // Hình ảnh sản phẩm
                        onItemClicked = {
                            onItemClicked(product) // Gọi hàm callback khi nhấn vào sản phẩm
                        }
                    )
                }
            }
        } else {
            Text(
                text = "Không có sản phẩm",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
        }
    }
}




fun uploadCartToServer(context: Context, cartItems: List<Product>) {
    // Chuyển danh sách sản phẩm thành chuỗi JSON
    val gson = Gson()
    val cartItemsJson = gson.toJson(cartItems)
    val userId = getUserId(context)
    println("User ID: $userId")
    // Kiểm tra nếu userId hợp lệ
    if (userId == -1) {
        Toast.makeText(context, "User ID không hợp lệ", Toast.LENGTH_SHORT).show()
        return
    }
    // Gọi API qua Retrofit
    RetrofitClient.instance.uploadCart(userId, cartItemsJson)
        .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                println(response.body())  // In JSON response để kiểm tra lỗi

                val loginResponse = response.body()
                if (loginResponse != null && loginResponse.success == true) {

                    Toast.makeText(context, "Đã gửi giỏ hàng thành công", Toast.LENGTH_SHORT).show()
                    // Xử lý điều hướng hoặc thông báo thành công
                } else {
                    Toast.makeText(context, "Gửi giỏ hàng thất bại: ${loginResponse?.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Lỗi kết nối: ${t.message}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }
        })
}


@Preview
@Composable
fun PreviewStoreScreen() {
    MyApplicationTheme {
        val viewModel: PetProfileViewModel = viewModel()
        val currentScreen = remember { mutableStateOf<Screen>(Screen.Store) } // Thêm currentScreen

        StoreScreen(viewModel = viewModel,currentScreen = currentScreen)
    }
}