package com.scan.e_commerce

import android.app.Application
import com.scan.data.di.dataUtilsModule
import com.scan.data.di.networkModule
import com.scan.data.di.repositoryModule
import com.scan.e_commerce.di.appVersionModule
import com.scan.e_commerce.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ECommerceApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinDi()
    }
    private fun initKoinDi() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ECommerceApp)
            modules(
                listOf(
                    appVersionModule,
                    networkModule,
                    repositoryModule,
                    dataUtilsModule,
                    productModule,
                )
            )
        }
    }
}