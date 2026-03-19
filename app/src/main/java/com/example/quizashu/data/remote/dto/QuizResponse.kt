package com.example.quizashu.data.remote.dto

import com.example.quizashu.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)