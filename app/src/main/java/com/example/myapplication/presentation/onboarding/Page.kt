package com.example.myapplication.presentation.onboarding

import android.media.audiofx.AudioEffect.Descriptor
import androidx.annotation.DrawableRes
import com.example.myapplication.R

data class Page(
    val title:String,
    val description: String,
    @DrawableRes val img: Int,
)
val pages = listOf(
    Page(
        title = "Hồ sơ theo dõi sức khoẻ Online",
        description = "Ứng dụng giúp bạn và các trung tâm thú y theo dõi tính trạng sức khoẻ thú cưng nhanh chóng và chính xác",
        img = R.drawable.img_cat_doctor
    ),
    Page(
        title = "Lên thực đơn và chế độ ăn khoa học",
        description = "Ứng dụng giúp bạn và các trung tâm thú y theo dõi tính trạng sức khoẻ thú cưng nhanh chóng và chính xác",
        img = R.drawable.img_schedule_cat
    ),
    Page(
        title = "Quản lý kho thực phẩm trong nhà",
        description = "Ứng dụng giúp bạn và các trung tâm thú y theo dõi tính trạng sức khoẻ thú cưng nhanh chóng và chính xác",
        img = R.drawable.img_box_cat
    ),
    Page(
        title = "Dịch vụ thú y bán kính 1km",
        description = "Ứng dụng giúp bạn và các trung tâm thú y theo dõi tính trạng sức khoẻ thú cưng nhanh chóng và chính xác",
        img = R.drawable.img_cat_study
    ),
    Page(
        title = "Mua sắm và giao hàng nhanh chóng",
        description = "Ứng dụng giúp bạn và các trung tâm thú y theo dõi tính trạng sức khoẻ thú cưng nhanh chóng và chính xác",
        img = R.drawable.img_cat
    ),
)