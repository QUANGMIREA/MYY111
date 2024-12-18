package com.example.myapplication.homescreen.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.Model.Product
import com.example.myapplication.homescreen.PetProfileViewModel


@Composable
fun searchbarStore (
    viewModel: PetProfileViewModel,
    cartCount: Int,
    Onclick : ()-> Unit,
    onProductClick: (Product) -> Unit
){
    var search by remember { mutableStateOf("") }
    // Lấy danh sách sản phẩm đã lọc từ ViewModel
    val filteredProducts by viewModel.filteredProducts.observeAsState(emptyList())
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    // Cập nhật danh sách sản phẩm khi từ khóa tìm kiếm thay đổi
    LaunchedEffect(search) {
        if (search.isNotBlank()) {
            viewModel.searchProducts(search)
        }
    }
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp, // Độ cao của bóng mờ (càng cao thì bóng càng đậm)
                )
                .background(Color.White) // Màu nền và góc bo của `Row`
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            BasicTextField(
                value = search,
                onValueChange = { newText ->
                    search  = newText
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(40.dp)
                    .padding(10.dp, 0.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    ) .focusRequester(focusRequester),
                textStyle = LocalTextStyle.current.copy( // Thêm `textStyle` để chỉnh cỡ chữ khi nhập văn bản
                    fontSize = 14.sp,
                    color = Color.Black // Đảm bảo văn bản có màu đen khi nhập
                ),

                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent, RoundedCornerShape(size = 10.dp))
                            .padding(horizontal = 8.dp, vertical = 8.dp), // Đảm bảo không có padding bên trong
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "")
                        Box(modifier = Modifier.weight(1f)) {
                            if (search.isEmpty()) { // Hiển thị placeholder khi không có giá trị
                                Text(text = "Search pet food", color = Color.Gray, fontSize = 14.sp)
                            }
                            innerTextField() // Hiển thị TextField thực tế
                        }
                        // Dấu "x" để xóa chữ đã viết
                        if (search.isNotEmpty()) {
                            IconButton(onClick = {
                                search = "" // Xóa nội dung khi nhấn vào dấu "x"
                                keyboardController?.hide() // Ẩn bàn phím
                                focusRequester.requestFocus() // Mất focus khỏi thanh tìm kiếm
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Search",
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            )
            IconButton(Onclick) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(3.dp)
                ){
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector =  Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.Gray
                    )
                    if(cartCount>0){
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color.Red, shape = CircleShape)
                                .align(Alignment.TopEnd)

                        ){
                            Text(
                                text = cartCount.toString(),
                                color = Color.White,
                                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }

        }
        // Danh sách sản phẩm tìm kiếm
        // Hiển thị danh sách sản phẩm tìm kiếm chỉ khi có từ khóa tìm kiếm
        if (search.isNotEmpty()) {
            // Danh sách sản phẩm tìm kiếm
            if (filteredProducts.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray.copy(alpha = 0.1f)) // Nền mờ phía dưới
                        .padding(16.dp) // Padding cho các sản phẩm
                ) {
                    filteredProducts.forEach{ product ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.White)
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .clickable {
                                        search = "" // Xóa từ khóa tìm kiếm
                                        keyboardController?.hide() // Ẩn bàn phím
                                        focusRequester.requestFocus() // Mất focus khỏi thanh tìm kiếm
                                        onProductClick(product)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Hiển thị Hình Ảnh
                                Image(
                                    painter = rememberAsyncImagePainter(model = product.hinhanh),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp).padding(10.dp)// Adjust the size as needed
                                )
                                // Hiển thị tên sản phẩm trong Card
                                Text(
                                    text = product.tensanpham,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                        }
                    }
                }
            } else {
                // Hiển thị thông báo khi không có sản phẩm nào được tìm thấy
                Text(
                    text = "No products found",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }

}