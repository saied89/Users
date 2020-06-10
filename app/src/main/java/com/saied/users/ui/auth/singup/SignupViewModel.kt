package com.saied.users.ui.auth.singup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    private val signupValidator = SignupFormValidator()

    private val _signupLiveData = MutableLiveData<Result<Boolean>>()

    val signupLiveData: LiveData<Result<Boolean>> = _signupLiveData

    fun submitSignUpData(signUpData: SignUpData) {
        _signupLiveData.value = signupValidator.validateFormData(signUpData)
    }
}