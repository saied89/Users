package com.saied.users.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun getUser(fullName: String, password: String) {
        _userLiveData.value = userRepository.getUser(fullName, password)
    }

}