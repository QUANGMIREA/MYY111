package com.example.myapplication.homescreen.store

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.LayoutDirection

class TopRoundedShape(
    private val radius: Float = 16f // Đặt giá trị mặc định, sẽ chuyển đổi trong `createOutline`
) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Chuyển đổi `dp` sang `px` bên trong phương thức này
        val cornerRadius = with(density) { radius.dp.toPx() }
        return Outline.Rounded(
            RoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                topLeftCornerRadius = CornerRadius(cornerRadius, cornerRadius),
                topRightCornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )
        )
    }
}
