package com.saied.users.ui.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository
import kotlinx.coroutines.launch

class ListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userListLiveData: LiveData<List<User>> = userRepository.getAllUsers()

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }

}