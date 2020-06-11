package com.saied.users.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saied.users.data.db.AppDatabase
import com.saied.users.data.model.User
import com.saied.users.data.model.UserDao
import com.saied.users.data.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module(createdAtStart = true) {
    single(createdAtStart = true) {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        val userDao: UserDao = get()
                        userDao.insertUser(
                            User(
                                "admin@user.com",
                                "admin",
                                "admin",
                                true
                            )
                        )
                    }
                }
            }).build().userDao()
    }

    single {
        UserRepository(get())
    }
}