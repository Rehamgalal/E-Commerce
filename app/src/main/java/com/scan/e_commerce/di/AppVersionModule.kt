package com.scan.e_commerce.di

import androidx.multidex.BuildConfig.VERSION_NAME
import com.scan.data.BuildConfig
import com.scan.data.di.DI_VERSION_CODE
import com.scan.data.di.DI_VERSION_NAME
import com.scan.e_commerce.BuildConfig.VERSION_CODE
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Koin module for app version name & code.
 */
val appVersionModule = module {

    single(named(DI_VERSION_NAME)) {
        VERSION_NAME
    }

    single(named(DI_VERSION_CODE)) {
        VERSION_CODE
    }

}