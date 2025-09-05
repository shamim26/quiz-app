package com.practice.quizapp.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.practice.quizapp.screens.QuestionsViewModel

@Composable
fun Question(viewModel: QuestionsViewModel) {
    val question =
        viewModel.data.value.data
            ?.toMutableList()

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        Log.d("Loading", "Question: Loaded")
        question?.forEach {
            Log.d("Question", "Question: ${it.question}")
        }
    }
}

@Composable
fun QuestionDisplay (){

}