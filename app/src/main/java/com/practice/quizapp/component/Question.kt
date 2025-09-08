package com.practice.quizapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.practice.quizapp.screens.QuestionsViewModel


@Composable
fun Question(viewModel: QuestionsViewModel) {
    val question = viewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableIntStateOf(0)
    }

    val currentScore = remember {
        mutableIntStateOf(0)
    }

    if (viewModel.data.value.loading == true) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    } else {
        val question = try {
            question?.get(questionIndex.intValue)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (question != null) {
            QuestionDisplay(
                question = question,
                questionIndex = questionIndex,
                currentScore = currentScore,
                viewModel = viewModel
            ) { isCorrect ->
                if (isCorrect) {
                    currentScore.intValue += 1
                }
                questionIndex.intValue = questionIndex.intValue + 1
            }
        }
    }
}