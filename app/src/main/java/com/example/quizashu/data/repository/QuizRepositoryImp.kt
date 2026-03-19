package com.example.quizashu.data.repository

import android.util.Log
import com.example.quizashu.data.remote.QuizApi
import com.example.quizashu.domain.model.Quiz
import com.example.quizashu.domain.repository.QuizRepository

class QuizRepositoryImp(private val quizApi: QuizApi) : QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String,
    ): List<Quiz> {
        val rsp =return quizApi.getQuizzes(amount, category, difficulty, type).results
        Log.d("quiz",rsp.toString())
        return rsp
    }



}