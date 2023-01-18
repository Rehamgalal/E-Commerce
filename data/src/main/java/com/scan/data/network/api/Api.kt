package com.scan.data.network.api

import com.scan.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("dynamodb-writer/")
    suspend fun getProducts(): Response<ProductResponse>
}