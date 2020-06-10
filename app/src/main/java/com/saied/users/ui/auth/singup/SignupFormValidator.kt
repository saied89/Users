package com.saied.users.ui.auth.singup

import org.apache.commons.validator.routines.EmailValidator
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.PasswordData
import org.passay.PasswordValidator

class SignupFormValidator {

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

    private val emailValidator = EmailValidator.getInstance()

    /**
     * Validates Signup Form Data.
     * @param signUpData submitted form data
     * @return in case of failure [Result.failure] with instance of [SignUpValidationException].
     * in case of success [Result.success]  with boolean true
     */
    fun validateFormData(signUpData: SignUpData): Result<Boolean> {
        val ruleRes = passwordValidator.validate(PasswordData(signUpData.password))
        return if (signUpData.fname.isEmpty() || signUpData.fname.isBlank())
            Result.failure(
                SignUpValidationException.FullNameEmptyException()
            )
        else if (!emailValidator.isValid(signUpData.email))
            Result.failure(
                SignUpValidationException.EmailValidationException()
            )
        else if (!ruleRes.isValid)
            Result.failure(
                SignUpValidationException.PasswordValidationException(
                    passwordValidator.getMessages(ruleRes)[0]  // only one message returned for convenience
                )
            )
        else if (signUpData.password != signUpData.cPassword)
            Result.failure(
                SignUpValidationException.PasswordConfirmException()
            )
        else
            Result.success(true)
    }
}

sealed class SignUpValidationException(msg: String) : Exception(msg) {
    class PasswordConfirmException :
        SignUpValidationException("Password and Confirm Password fields are not equal")

    class PasswordValidationException(msg: String) : SignUpValidationException(msg)
    class EmailValidationException : SignUpValidationException("Email address is not valid")
    class FullNameEmptyException : SignUpValidationException("Fullname is empty")
}