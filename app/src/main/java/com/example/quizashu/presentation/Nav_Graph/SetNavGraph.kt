package com.example.quizashu.presentation.Nav_Graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizashu.presentation.Quiz.QuizScreen
import com.example.quizashu.presentation.Quiz.QuizViewModel
import com.example.quizashu.presentation.Score.ScoreScreen
import com.example.quizashu.presentation.home.HomeScreen
import com.example.quizashu.presentation.home.componentts.ViewModel.HomeViewModel

@Composable
fun SetNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {


        composable(route = Routes.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }

        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER) { type = NavType.IntType },
                navArgument(ARG_KEY_QUIZ_CATEGORY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_DIFFICULTY) { type = NavType.StringType },
                navArgument(ARG_KEY_QUIZ_TYPE) { type = NavType.StringType }
            )
        ) {
            val numberOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            val quizViewModel: QuizViewModel = hiltViewModel()
            val state = quizViewModel.quizList.collectAsState()

            QuizScreen(
                numOfQuiz = numberOfQuizzes!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state.value,
                navController = navController

            )
        }

        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY) { type = NavType.IntType },
                navArgument(CORRECT_ANS_KEY) { type = NavType.IntType }
            )
        ) {
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)

            ScoreScreen(
                numOfQuestions = numOfQuestions!!,
                numOfCorrectAns = numOfCorrectAns!!,
                navController = navController

            )


        }


    }
}