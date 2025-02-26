package com.gabrielsulzbacker.addressfinder

import android.app.Application
import com.gabrielsulzbacker.addressfinder.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AddressFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AddressFinderApplication)
            modules(appModule)
        }
    }
}