package com.example.questy.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.questy.data.QuesRepositoryImpl
import com.example.questy.domain.usecase.GetListQuestUseCase

class MainViewModel(
    private val application: Application
) : ViewModel() {

    private val repository = QuesRepositoryImpl(application)
    private val getListQuestUseCase = GetListQuestUseCase(repository)
    val questList = getListQuestUseCase()

}