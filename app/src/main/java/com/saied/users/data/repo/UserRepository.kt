package com.saied.users.data.repo

import androidx.lifecycle.LiveData
import com.saied.users.data.model.User
import com.saied.users.data.model.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(email: String, password: String): User? =
        userDao.getUser(email, password)

    suspend fun getUser(id: Int): User =
        userDao.getUser(id)

    fun getAllUsers(): LiveData<List<User>> =
        userDao.getAllUsers()

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

}