package com.saied.users

import android.app.Application
import com.saied.users.di.appMdoule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appMdoule))
        }
    }
}