package com.example.complicated

import android.app.Application
import com.example.complicated.di.appModule
import com.example.complicated.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComplicatedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ComplicatedApplication)
            modules(appModule, searchModule)
        }
    }
}