package com.example.quizashu.domain.repository

import com.example.quizashu.domain.model.Quiz


interface QuizRepository {
    suspend fun getQuizzes(amount: Int , category: Int, difficulty: String , type : String) : List<Quiz>
}