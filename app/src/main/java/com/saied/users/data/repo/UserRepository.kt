package com.saied.users.data.repo

import com.saied.users.data.model.User
import com.saied.users.data.model.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(email: String, password: String): User? = withContext(Dispatchers.IO) {
        userDao.getUser(email, password)
    }
}