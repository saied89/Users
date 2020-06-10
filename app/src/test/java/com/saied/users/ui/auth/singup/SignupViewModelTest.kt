package com.saied.users.ui.auth.singup

import org.junit.Test

import org.junit.Assert.*
import java.lang.AssertionError

class SignupViewModelTest {

    @Test
    fun `none equal password and confirm_password fail validation`() {
        val subject = SignupViewModel()

        val res = subject.validateFormData(SignUpData("","", "pas", "Pas"))

        assert(res.isFailure)
        assert(res.exceptionOrNull() is AssertionError)
    }

    @Test
    fun `invalid password fails with appropriate error`() {
        val subject = SignupViewModel()

        val res = subject.validateFormData(SignUpData("","", "Pas", "Pas"))
        print(res.exceptionOrNull()?.message)
        assert(res.isFailure)
        assert(res.exceptionOrNull() is AssertionError)
    }
}