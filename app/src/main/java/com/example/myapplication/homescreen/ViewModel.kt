package com.example.myapplication.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Model.CartItem
import com.example.myapplication.Model.CartResponse
import com.example.myapplication.Model.CartState
import com.example.myapplication.Model.LoginResponse
import com.example.myapplication.Model.Product
import com.example.myapplication.Model.ProductsApiResponse
import com.example.myapplication.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetProfileViewModel : ViewModel() {
    // Toàn bộ danh sách sản phẩm từ API
    private val _allProducts = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>> get() = _allProducts

    // Danh sách sản phẩm đã được lọc dựa trên tìm kiếm
    private val _filteredProducts = MutableLiveData<List<Product>>()
    val filteredProducts: LiveData<List<Product>> get() = _filteredProducts

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        RetrofitClient.instance.getProducts()
            .enqueue(object : Callback<ProductsApiResponse> {
                override fun onResponse(
                    call: Call<ProductsApiResponse>,
                    response: Response<ProductsApiResponse>
                ) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val productsList = response.body()?.result ?: listOf()
                        _allProducts.value = productsList
                        _filteredProducts.value = productsList // Khởi tạo filteredProducts bằng tất cả sản phẩm
                    }
                }

                override fun onFailure(call: Call<ProductsApiResponse>, t: Throwable) {
                    // Xử lý lỗi tại đây (ví dụ: hiển thị thông báo lỗi)
                }
            })
    }

    fun searchProducts(query: String) {
        val currentList = _allProducts.value ?: emptyList()
        val filtered = if (query.isBlank()) {
            currentList
        } else {
            currentList.filter {
                it.tensanpham.contains(query, ignoreCase = true)
            }
        }
        _filteredProducts.value = filtered
    }
}

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems
    private val _cartState = MutableLiveData<CartState>()
    val cartState: LiveData<CartState> = _cartState

    fun fetchCartItems(userId: Int) {
        RetrofitClient.instance.getCartItems(userId).enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    val cartResponse = response.body()
                    if (cartResponse != null && cartResponse.success) {
                        _cartItems.value = cartResponse.data
                        _cartState.value = cartResponse.cart_state
                    }
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
    fun updateCartItemQuantity(userId: Int, productId: Int, newQuantity: Int) {
        RetrofitClient.instance.updateCartItemQuantity(userId, productId, newQuantity)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Xử lý thành công (cập nhật lại giỏ hàng nếu cần)
                        fetchCartItems(userId) // Lấy lại giỏ hàng để làm mới dữ liệu
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}
