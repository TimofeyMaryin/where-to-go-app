package com.where.to.go.core.cases.di

import com.where.to.go.core.cases.auth.AuthUseCase
import com.where.to.go.core.cases.auth.AuthUseCaseImpl
import com.where.to.go.core.cases.user.UserUseCase
import com.where.to.go.core.cases.user.UserUseCaseImpl
import com.where.to.go.domain.contract.AuthRepository
import com.where.to.go.domain.contract.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(authRepository)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCaseImpl(userRepository)
    }

}