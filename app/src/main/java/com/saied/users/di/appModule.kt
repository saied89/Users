package com.saied.users.di

import com.saied.users.ui.admin.ListViewModel
import com.saied.users.ui.auth.login.LoginViewModel
import com.saied.users.ui.auth.singup.SignupViewModel
import com.saied.users.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appMdoule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}