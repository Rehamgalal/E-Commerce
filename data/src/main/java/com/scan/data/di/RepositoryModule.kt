package com.scan.data.di

import com.scan.data.repository.ProductRemoteDataSource
import com.scan.data.repository.ProductRemoteDataSourceImpl
import com.scan.data.repository.ProductRepo
import com.scan.data.repository.ProductRepoImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val repositoryModule = module {

//Product Repo
    factoryOf(::ProductRemoteDataSourceImpl) bind ProductRemoteDataSource::class
    factory<ProductRepo> { ProductRepoImpl(get(), get()) }
}