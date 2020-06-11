package com.saied.users.di

import com.saied.users.data.repo.UserRepository
import com.saied.users.ui.admin.AdminViewModel
import com.saied.users.ui.auth.login.LoginViewModel
import com.saied.users.ui.auth.singup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appMdoule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { SignupViewModel(get()) }

    viewModel { AdminViewModel(get()) }
}