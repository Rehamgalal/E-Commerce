package com.scan.e_commerce.di

import com.scan.e_commerce.presentation.ProductMapper
import com.scan.e_commerce.presentation.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val productModule = module {
    factory { ProductMapper() }
    viewModelOf(::ProductViewModel)
}