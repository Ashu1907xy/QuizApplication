package com.example.quizashu.presentation.home.componentts

sealed class EventHomeScreen {

    data class SetNumberOfQuizzes( val numberOfQuizzes : Int): EventHomeScreen()
    data class SetQuizCategory( val category : String): EventHomeScreen()
    data class SetQuizDifficulty( val difficulty : String): EventHomeScreen()
    data class SetQuizType( val type : String): EventHomeScreen()



}