package com.example.quizashu.domain.di

import com.example.quizashu.data.remote.QuizApi
import com.example.quizashu.data.repository.QuizRepositoryImp
import com.example.quizashu.domain.repository.QuizRepository
import com.example.quizashu.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository): GetQuizzesUseCases {
        return GetQuizzesUseCases(quizRepository)
    }

}