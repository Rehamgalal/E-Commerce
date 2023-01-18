package com.scan.e_commerce.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.scan.e_commerce.R

@BindingAdapter("imageUrl")
fun loadFileImage(
    imageView: ImageView,
    imageUrl: String,
) {
    Glide.with(imageView).load(imageUrl).placeholder(R.drawable.default_image).into(imageView)
}