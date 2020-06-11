package com.saied.users.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun getUser(fullName: String, password: String) {
        viewModelScope.launch {
            _userLiveData.value = userRepository.getUser(fullName, password)
        }
    }

}