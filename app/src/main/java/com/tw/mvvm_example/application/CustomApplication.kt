package com.tw.mvvm_example.application

import android.app.Application
import com.tw.mvvm_example.di.mapperModule
import com.tw.mvvm_example.di.networkModule
import com.tw.mvvm_example.di.persistenceModule
import com.tw.mvvm_example.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(listOf(networkModule, viewModelsModule, persistenceModule, mapperModule))
        }
    }
}