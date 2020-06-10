package com.saied.users.di

import com.saied.users.data.repo.UserRepository
import org.koin.dsl.module

val appMdoule = module {
    single {
        UserRepository()
    }
}