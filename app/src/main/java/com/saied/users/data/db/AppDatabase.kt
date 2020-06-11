package com.saied.users.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saied.users.data.model.User
import com.saied.users.data.model.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}