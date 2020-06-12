package com.saied.users.ui.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository

class ListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userListLiveData: LiveData<List<User>> = userRepository.getAllUsers()

}