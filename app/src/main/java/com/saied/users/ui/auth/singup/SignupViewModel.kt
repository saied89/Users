package com.saied.users.ui.auth.singup

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.passay.*

class SignupViewModel : ViewModel() {

    private val _signupLiveData = MutableLiveData<Result<Boolean>>()

    val signupLiveData: LiveData<Result<Boolean>> = _signupLiveData

    /**
     * Password Validator from Passay library.
     * Rules for requiring an uppercase letter and a digit in passwords
     */
    private val passwordValidator = PasswordValidator(
        listOf(
            CharacterRule(EnglishCharacterData.UpperCase),
            CharacterRule(EnglishCharacterData.Digit)
        )
    )

    fun submitSignUpData(signUpData: SignUpData) {

    }

    @VisibleForTesting
    fun validateFormData(signUpData: SignUpData): Result<Boolean> =
        if (signUpData.password != signUpData.cPassword)
            Result.failure(
                AssertionError("Password and Confirm Password fields are not equal")
            )
        else {
            val ruleRes = passwordValidator.validate(PasswordData(signUpData.password))
            if(ruleRes.isValid)
                Result.success(true)
            else
                Result.failure(
                    AssertionError(
                        passwordValidator.getMessages(ruleRes)[0]
                    )
                )
        }
}