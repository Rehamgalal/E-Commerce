package com.scan.e_commerce.presentation

import com.scan.data.models.ProductResponse

class ProductMapper {

    fun toProductUiModel(productResponse: ProductResponse.Product): ProductUiState.ProductModel {
        return ProductUiState.ProductModel(
            productResponse.uid,
            productResponse.name,
            productResponse.price,
            productResponse.created_at,
            productResponse.image_ids,
            productResponse.image_urls,
            productResponse.image_urls_thumbnails
        )
    }
}