package com.example.quizashu.presentation.home.componentts.ViewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.quizashu.presentation.home.componentts.EventHomeScreen
import com.example.quizashu.presentation.home.componentts.StateHomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {


    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event: EventHomeScreen) {
        when (event) {
            is EventHomeScreen.SetNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuizzes = event.numberOfQuizzes)
            }

            is EventHomeScreen.SetQuizCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)
            }

            is EventHomeScreen.SetQuizDifficulty -> {
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }

            is EventHomeScreen.SetQuizType -> {
                _homeState.value = homeState.value.copy(type = event.type)
            }

            else -> {

            }
        }
    }


}