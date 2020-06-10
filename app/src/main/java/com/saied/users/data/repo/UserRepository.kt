package com.saied.users.data.repo

import com.saied.users.data.model.User

class UserRepository {
    fun getUser(fullName: String, password: String): User? {
        // placeholder logic for db implementation
        if(fullName == "admin" && password == "admin")
            return User("admin", "admin", true)
        else return null
    }
}