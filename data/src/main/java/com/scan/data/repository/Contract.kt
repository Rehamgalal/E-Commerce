package com.scan.data.repository

import com.scan.base.data.Resource
import com.scan.data.models.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    suspend fun getProducts(): Flow<Resource<ProductResponse>>
}

interface ProductRemoteDataSource {
    suspend fun getProducts(): ProductResponse
}