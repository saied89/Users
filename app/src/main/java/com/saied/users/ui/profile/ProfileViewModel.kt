package com.saied.users.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun getUser(userId: Int) {
        viewModelScope.launch {
            _userLiveData.value = repository.getUser(userId)
        }
    }

}