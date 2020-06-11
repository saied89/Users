package com.saied.users.data.repo

import androidx.lifecycle.LiveData
import com.saied.users.data.model.User
import com.saied.users.data.model.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(email: String, password: String): User? =
        userDao.getUser(email, password)


    fun getAllUsers(): LiveData<List<User>> =
        userDao.getAllUsers()

}