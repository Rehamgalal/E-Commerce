package com.scan.data.repository

import com.scan.base.data.BaseRepo
import com.scan.base.data.Resource
import com.scan.base.utils.NetworkConnectivityHelper
import com.scan.data.models.ProductResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ProductRepoImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    networkConnectivityHelper: NetworkConnectivityHelper,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseRepo(networkConnectivityHelper,ioDispatcher) ,ProductRepo {
    override suspend fun getProducts(): Flow<Resource<ProductResponse>> {
        return networkOnlyFlow { productRemoteDataSource.getProducts() }
    }
}