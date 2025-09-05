package com.practice.quizapp.repository

import android.util.Log
import com.practice.quizapp.data.DataOrException
import com.practice.quizapp.model.QuestionItem
import com.practice.quizapp.network.QuestionApi
import javax.inject.Inject

class QuestionRepository
    @Inject
    constructor(
        private val api: QuestionApi,
    ) {
        private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

        suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
            try {
                dataOrException.loading = true
                dataOrException.data = api.getAllQuestions()
                if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
            } catch (e: Exception) {
                dataOrException.e = e
                dataOrException.loading = false
                Log.d("Exc", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
            }
            return dataOrException
        }
    }
