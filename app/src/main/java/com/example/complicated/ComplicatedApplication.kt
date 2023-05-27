package com.example.complicated

import android.app.Application
import com.example.complicated.di.appModule
import com.example.complicated.di.searchModule
import com.martini.complicated.details.detailsModule
import com.martini.data.songDetailModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComplicatedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ComplicatedApplication)
            modules(
                appModule,
                searchModule,
                songDetailModule,
                detailsModule
            )
        }
    }
}