package com.saied.users.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE :email = email AND :password = password")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT * FROM user WHERE :id = id")
    suspend fun getUser(id: Int): User

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Update
    fun update(user: User)

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}