package com.saied.users.ui.auth.singup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saied.users.data.model.User
import com.saied.users.data.repo.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val signupValidator = SignupFormValidator()

    private val _signupLiveData = MutableLiveData<Result<Boolean>>()

    // Path of the selected profile picture.
    val imagePathLiveData = MutableLiveData<String>()

    val signupLiveData: LiveData<Result<Boolean>> = _signupLiveData

    fun submitSignUpData(signUpData: SignUpData) {
        viewModelScope.launch {
            val res = signupValidator.validateFormData(signUpData)
            if (res.isSuccess)
                userRepository.insertUser(
                    User(
                        fullName = signUpData.fname,
                        email = signUpData.email,
                        password = signUpData.password,
                        picturePath = signUpData.picturePath,
                        isAdmin = false
                    )
                )
            _signupLiveData.value = res
        }
    }
}