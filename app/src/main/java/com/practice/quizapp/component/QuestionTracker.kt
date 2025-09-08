package com.practice.quizapp.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.practice.quizapp.util.AppColors

@Composable
fun QuestionTracker(counter: Int, outOf: Int ) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = AppColors.MintGreen,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 27.sp
                )
            ) {
                append("Question: $counter/")
                withStyle(
                    style = SpanStyle(
                        color = AppColors.UclBlue,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                ) {
                    append("$outOf")
                }
            }
        }
    })
}
