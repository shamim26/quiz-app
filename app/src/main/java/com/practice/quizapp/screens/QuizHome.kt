package com.practice.quizapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.practice.quizapp.Question

@Composable
fun QuizHome(
    modifier: Modifier,
    viewModel: QuestionsViewModel = hiltViewModel(),
) {
    Question(viewModel)
}
