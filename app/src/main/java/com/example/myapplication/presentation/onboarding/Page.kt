package com.example.myapplication.presentation.onboarding

import android.media.audiofx.AudioEffect.Descriptor
import androidx.annotation.DrawableRes

data class Page(
    val title:String,
    val descriptor: String,
    @DrawableRes val image: Int
)
