package com.scan.data.models

import com.scan.base.data.BaseResponse

data class ProductResponse(
    val results: List<Product>
) : BaseResponse(){
    data class Product(
        val created_at: String,
        val image_ids: List<String>,
        val image_urls: List<String>,
        val image_urls_thumbnails: List<String>,
        val name: String,
        val price: String,
        val uid: String
    )
}