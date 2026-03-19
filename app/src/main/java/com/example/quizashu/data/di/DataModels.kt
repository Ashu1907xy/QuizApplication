package com.example.quizashu.data.di

import com.example.quizashu.data.remote.QuizApi
import com.example.quizashu.data.repository.QuizRepositoryImp
import com.example.quizashu.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModels {

    @Provides
    @Singleton
    fun provideQuizApi(): QuizApi {
        return Retrofit.Builder().baseUrl("https://opentdb.com/")
            .addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizRepository(quizApi: QuizApi): QuizRepository {
        return QuizRepositoryImp(quizApi)
    }

}