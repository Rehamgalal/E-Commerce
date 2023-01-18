package com.scan.e_commerce.presentation

import androidx.lifecycle.viewModelScope
import com.scan.base.data.Resource
import com.scan.base.view.BaseViewModel
import com.scan.data.repository.ProductRepo
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepo: ProductRepo, private val productMapper: ProductMapper): BaseViewModel<ProductUiState>(
    ProductUiState.Loading
) {

    init {
        getProducts()
    }

    var productModel : ProductUiState.ProductModel?=null
    private fun getProducts() {
        viewModelScope.launch {
            productRepo.getProducts().collect { resource ->
                when (resource) {
                    is Resource.Loading -> updateState(ProductUiState.Loading)
                    is Resource.Error -> updateState(
                        ProductUiState.Error(
                            resource.exception.message ?: ""
                        )
                    )
                    is Resource.Success -> updateState(ProductUiState.Success(resource.data?.results?.map { product ->
                        productMapper.toProductUiModel(
                            product
                        )
                    } ?: emptyList()))
                }
            }
        }
    }

    fun openProductDetails(product: ProductUiState.ProductModel) {
        productModel = product
    }
}