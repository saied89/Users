package com.saied.users.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE :email = email AND :password = password")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT * FROM user WHERE :email = email")
    suspend fun getUser(email: String): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}