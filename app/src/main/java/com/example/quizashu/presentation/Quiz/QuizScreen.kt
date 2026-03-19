package com.example.quizashu.presentation.Quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Constraints
import com.example.quizashu.R
import com.example.quizashu.presentation.Quiz.component.QuizInterface
import com.example.quizashu.presentation.Quiz.component.ShimmerEffectQuizInterface
import com.example.quizashu.presentation.home.componentts.ButtonBox
import com.example.quizashu.presentation.home.componentts.QuizAppBar
import com.example.quizashu.presentation.util.Constants
import com.example.quizashu.presentation.util.Dimens
import com.example.quizashu.presentation.util.Dimens.MediumCornerRadius
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

@Composable
fun QuizScreen(
    numOfQuiz: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
) {
    LaunchedEffect(key1 = Unit) {
        val difficulty = when (quizDifficulty) {
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }
        val type = when (quizType) {
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }

        Log.d("quiz", "bbb")

        event(
            EventQuizScreen.GetQuizzes(
                numOfQuiz,
                Constants.categoriesMap[quizCategory]!!,
                difficulty,
                type
            )
        )

    }
    Column(modifier = Modifier.fillMaxSize()) {

        QuizAppBar(quizCategory = quizCategory) {

        }


        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Questions : $numOfQuiz",
                    color = colorResource(id = R.color.blue_grey)

                )

                Text(
                    text = quizDifficulty,
                    color = colorResource(id = R.color.blue_grey)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight)) //

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.VerySmallViewHeight)
                    .clip(
                        RoundedCornerShape(MediumCornerRadius)
                    )
                    .background(
                        colorResource(id = R.color.blue_grey)
                    )
            )

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            // quiz interface

            if (quizFetched(state)) {


                val pagerState = rememberPagerState() { state.quizState.size }

                HorizontalPager(state = pagerState) { index ->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        quizState = state.quizState[index],
                        onOptionSelected = { selectedIndex ->

                            event(EventQuizScreen.SetOptionSelected(index, selectedIndex))
                        },
                        qNumber = index + 1,
                    )
                }


                val buttonText by remember {
                    derivedStateOf {
                        when (pagerState.currentPage) {
                            0 -> {
                                listOf("", "Next")
                            }

                            state.quizState.size - 1 -> {
                                listOf("Previous", "Submit")

                            }

                            else -> {
                                listOf("Previous", "Next")

                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.MediumPadding)
                        .navigationBarsPadding()
                )
                {

                    val scope = rememberCoroutineScope()
                    if (buttonText[0].isNotEmpty()) {


                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fontSize = Dimens.SmallTextSize,
                            fraction = 0.43f,

                            ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    } else {


                        ButtonBox(
                            text = "",
                            fontSize = Dimens.SmallTextSize,
                            fraction = 0.43f,
                            containerColor = colorResource(id = R.color.mid_night_blue),
                            borderColor = colorResource(id = R.color.mid_night_blue)

                        ) {}
                    }




                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(id = R.color.orange),

                        containerColor = if (pagerState.currentPage == state.quizState.size - 1)
                            colorResource(id = R.color.orange)
                        else colorResource(R.color.dark_slate_blue),

                        fontSize = Dimens.SmallTextSize,  // SmallTextSize
                        fraction = 1f,
                        textColor = colorResource(R.color.white),

                        ) {
                        scope.launch {
                            if (pagerState.currentPage == state.quizState.size - 1) {


                                for (i in state.quizState) {
                                    Log.d("selected", i.selectedOptions.toString())
                                }


                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                        }
                    }
                }
            }

        }

    }


}


@Composable
fun quizFetched(state: StateQuizScreen): Boolean {
    return when {
        state.isLoading -> {
            ShimmerEffectQuizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else -> {
            Text(text = state.error, color = colorResource(id = R.color.white))
            false
        }

    }
}

