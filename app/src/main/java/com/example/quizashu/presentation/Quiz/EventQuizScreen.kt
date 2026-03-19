package com.example.quizashu.presentation.Quiz

import com.example.quizashu.presentation.home.componentts.EventHomeScreen

sealed class EventQuizScreen {
    data class GetQuizzes(
        val numberOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String,
    ) : EventQuizScreen()


    data class SetOptionSelected(
        val quizStateIndex: Int,
        val selectedOption: Int
    ) :
        EventQuizScreen()
}

