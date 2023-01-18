package com.scan.data.repository

import com.scan.base.data.BaseRemoteDataSource
import com.scan.data.models.ProductResponse
import com.scan.data.network.api.Api

class ProductRemoteDataSourceImpl(private val api: Api) : ProductRemoteDataSource , BaseRemoteDataSource(){
    override suspend fun getProducts(): ProductResponse {
        return makeRequest { api.getProducts() }
    }
}