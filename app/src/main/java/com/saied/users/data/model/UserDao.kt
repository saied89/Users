package com.saied.users.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("select * FROM user WHERE :email = email AND :password = password")
    fun getUser(email: String, password: String): User?

    @Insert
    fun insertUser(user: User)
}