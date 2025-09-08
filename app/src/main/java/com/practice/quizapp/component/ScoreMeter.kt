package com.practice.quizapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.quizapp.util.AppColors

@Composable
fun ScoreMeter(score: Int) {

    val progressFactor = remember(score) {
        mutableFloatStateOf(score * 0.005f)
    }

    val gradient = Brush.linearGradient(
        listOf(
            Color(0xFFE1BEE7),
            Color(0xFF80CBC4)
        )
    )
    Row(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.MintGreen,
                        AppColors.UclBlue
                    )
                ),
                shape = RoundedCornerShape(34.dp)
            )
            .clip(
                RoundedCornerShape(50)
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .fillMaxWidth(progressFactor.floatValue)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            )
        ) {

            Text(
                score.toString(),
                fontSize = 15.sp,
                color = AppColors.DelftBlue,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                textAlign = TextAlign.Center

            )
        }
    }

}