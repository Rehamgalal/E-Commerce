package com.scan.e_commerce.presentation

import com.scan.base.view.UiState

sealed class ProductUiState : UiState {

    object Loading : ProductUiState()

    data class Success(val products: List<ProductModel>) : ProductUiState()

    data class Error(val failureMessage: String) : ProductUiState()

    data class ProductModel(val id: String, val name: String, val price: String, val createdAt: String, val imageIds: List<String>, val imageUrls: List<String>, val imagesThumbnails: List<String>)
}