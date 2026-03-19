package com.example.quizashu.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.quizashu.presentation.Nav_Graph.Routes
import com.example.quizashu.presentation.home.componentts.AppDropDownMenu
import com.example.quizashu.presentation.home.componentts.ButtonBox
import com.example.quizashu.presentation.home.componentts.EventHomeScreen
import com.example.quizashu.presentation.home.componentts.HomeHeader
import com.example.quizashu.presentation.home.componentts.StateHomeScreen
import com.example.quizashu.presentation.home.componentts.ViewModel.HomeViewModel
import com.example.quizashu.presentation.util.Constants
import com.example.quizashu.presentation.util.Dimens.MediumPadding
import com.example.quizashu.presentation.util.Dimens.MediumSpacerHeight
import com.example.quizashu.presentation.util.Dimens.SmallSpacerHeight

@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (EventHomeScreen) -> Unit,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader()


        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Number of Questions : ",
            menuList = Constants.numbersAsString,
            text = state.numberOfQuizzes.toString(),
            onDropDownClick = { event(EventHomeScreen.SetNumberOfQuizzes(it.toInt())) })

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Category : ",
            menuList = Constants.categories,
            text = state.category,
            onDropDownClick = { event(EventHomeScreen.SetQuizCategory(it)) })

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty : ",
            menuList = Constants.difficulty,
            text = state.difficulty,
            onDropDownClick = { event(EventHomeScreen.SetQuizDifficulty(it)) })

        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type : ",
            menuList = Constants.type,
            text = state.type,
            onDropDownClick = { event(EventHomeScreen.SetQuizType(it)) })

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        ButtonBox(text = "Generate Quiz", padding = MediumPadding) {

            Log.d(
                "QuizApp",
                "${state.numberOfQuizzes} ${state.category} ${state.difficulty} ${state.type}"
            )

            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    state.numberOfQuizzes,
                    state.category,
                    state.difficulty,
                    state.type
                )
            )
        }


    }
}