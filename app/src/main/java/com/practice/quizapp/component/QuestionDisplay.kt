package com.practice.quizapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.quizapp.model.QuestionItem
import com.practice.quizapp.screens.QuestionsViewModel
import com.practice.quizapp.util.AppColors

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    currentScore: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClicked: (Boolean) -> Unit = {}

) {

    val choicesState = remember(question) {
        question.choices.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        { selectedIndex ->
            answerState.value = selectedIndex
            val isCorrect = choicesState[selectedIndex] == question.answer
            correctAnswerState.value = isCorrect
        }
    }

    val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = AppColors.RaisinBlack,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (currentScore.value >= 3) ScoreMeter(score = currentScore.value)
            QuestionTracker(
                counter = questionIndex.value + 1,
                outOf = viewModel.getTotalQuestionCount()
            )
            DashLine(dashPathEffect)

            Column {
                Text(
                    question.question,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f)
                        .padding(vertical = 20.dp),
                    fontSize = 20.sp,
                    color = AppColors.PowderBlue,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp
                )
                //Choices
                choicesState.forEachIndexed { index, answerText ->
                    val isSelected = answerState.value == index
                    val isCorrectSelection = correctAnswerState.value == true && isSelected
                    val isIncorrectSelection = correctAnswerState.value == false && isSelected

                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                            .border(
                                width = 3.dp, brush = Brush.linearGradient(
                                    colors =
                                        when {
                                            isCorrectSelection -> listOf( // Green for correct selected
                                                Color.Green.copy(alpha = 0.5f),
                                                Color.Green.copy(alpha = 0.3f)
                                            )

                                            isIncorrectSelection -> listOf( // Red for incorrect selected
                                                Color.Red.copy(alpha = 0.5f),
                                                Color.Red.copy(alpha = 0.3f)
                                            )

                                            else -> listOf( // Default
                                                AppColors.PowderBlue, AppColors.UclBlue
                                            )
                                        }
                                ), shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50
                                )
                            )
                            .background(Color.Transparent)
                            .clickable(
                                enabled = correctAnswerState.value == null, // Disable after an answer is chosen for this question
                                role = Role.RadioButton,
                                onClick = { updateAnswer(index) }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = null, // Click handled by Row
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = when {
                                    isCorrectSelection -> Color.Green.copy(alpha = 0.7f)
                                    isIncorrectSelection -> Color.Red.copy(alpha = 0.7f)
                                    else -> MaterialTheme.colorScheme.primary // Or your default radio selected color
                                }
                                // You might want to set unselectedColor and disabledSelectedColor too
                            )
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        color = when {
                                            isCorrectSelection -> Color.Green.copy(alpha = 0.9f)
                                            isIncorrectSelection -> Color.Red.copy(alpha = 0.9f)
                                            else -> AppColors.PowderBlue
                                        }
                                    )
                                ) { append(answerText) }
                            },
                            modifier = Modifier.padding(6.dp),
                            // color = AppColors.MintGreen, // Color is now handled by SpanStyle
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                Button(
                    onClick = { onNextClicked(correctAnswerState.value ?: false) },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Next", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
            }
        }

    }
}