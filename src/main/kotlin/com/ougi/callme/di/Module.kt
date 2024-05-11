package com.ougi.callme.di

import com.ougi.callme.data.repository.AuthRepositoryImpl
import com.ougi.callme.domain.repository.AuthRepository
import com.ougi.callme.domain.usecase.AuthRequestUseCase
import com.ougi.callme.domain.usecase.AuthRequestUseCaseImpl
import io.ktor.client.*
import io.ktor.client.engine.java.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single { HttpClient(Java) }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::AuthRequestUseCaseImpl) { bind<AuthRequestUseCase>() }
}