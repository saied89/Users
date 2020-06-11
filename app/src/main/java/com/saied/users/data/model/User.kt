package com.saied.users.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    val fullName: String,
    val password: String,
    val picturePath: String?,
    val isAdmin: Boolean
)