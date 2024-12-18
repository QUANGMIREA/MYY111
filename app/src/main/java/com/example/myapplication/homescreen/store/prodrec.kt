package com.example.myapplication.homescreen.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.myapplication.Model.Product

import com.example.myapplication.homescreen.PetProfileViewModel
import com.example.myapplication.presentation.common.Containerdemo
import com.example.myapplication.presentation.common.ViewContainer2
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun prorec(
    viewModel: PetProfileViewModel,
    text4: String
) {
    // Trạng thái để lưu trữ sản phẩm đã chọn và kiểm soát bottom sheet
    val selectedProduct = remember { mutableStateOf<Product?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            selectedProduct.value?.let { product ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(data = product.hinhanh),
                        contentDescription = "Product Image",
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.tensanpham, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = product.motasanpham)
                    Text(text = product.giasp)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            val scrollState = rememberScrollState()
            val products = viewModel.filteredProducts.observeAsState(initial = emptyList()).value
            val dogProducts = products.filter { product -> product.type == "1" }

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
                            text1 = product.tensanpham,
                            text2 = product.motasanpham,
                            text3 = product.giasp,
                            painter = rememberImagePainter(data = product.hinhanh), // Thay thế bằng hình ảnh thực tế
                            onItemClicked = {
                                selectedProduct.value = product
                                coroutineScope.launch {
                                    bottomSheetState.show()
                                }
                            }
                        )
                    }
                }
            } else {
                Text(
                    text = "No Product available",
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
}

@Preview(showBackground = true)
@Composable
fun prorecreview(){
    MyApplicationTheme(){
        val viewModel: PetProfileViewModel = viewModel()
        prorec(viewModel = viewModel,text4 = "Hello")
    }
}
