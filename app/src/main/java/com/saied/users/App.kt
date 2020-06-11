package com.saied.users

import android.app.Application
import com.saied.users.data.model.UserDao
import com.saied.users.di.appMdoule
import com.saied.users.di.dbModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appMdoule, dbModule))
            get<UserDao>() // Ensure db eagerly instantiated and data preloading is done. https://github.com/InsertKoinIO/koin/issues/608
        }
    }
}