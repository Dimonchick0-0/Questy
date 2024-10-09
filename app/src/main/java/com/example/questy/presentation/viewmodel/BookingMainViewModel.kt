package com.example.questy.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questy.data.QuesRepositoryImpl
import com.example.questy.domain.model.Quest
import com.example.questy.domain.model.User
import com.example.questy.domain.repository.QuestRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookingMainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = QuesRepositoryImpl(application)
    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private var _errorInputDate = MutableLiveData<Boolean>()
    val errorInputDate: LiveData<Boolean>
        get() = _errorInputDate

    private var _questBooking = MutableLiveData<User>()
    val questBooking: LiveData<User>
        get() = _questBooking

    fun bookingQuestItem(
        inputName: String?,
        inputNumber: String?,
        inputQuestDate: String?
    ) {
        val name = parseInputName(inputName)
        val numPhone = parseInputNumberPhone(inputNumber)
        val dateQuest = parseInputDate(inputQuestDate)
        validateInput(name, numPhone, dateQuest)
    }

    fun userBookingAQuest(name: String, num: Long, data: Int, questTitle: String) {
        val questItem = Quest(
            image = null,
            title = questTitle
        )
        val validateUser = validateInput(name, num, data)
        if (validateUser) {
             viewModelScope.launch {
                 val user = User(name, num, data, questItem)
                 _questBooking.value = user
                 repository.bookingQuest(user)
             }
        }
    }

    private fun parseInputName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseInputNumberPhone(inputNumber: String?): Long {
        return try {
            inputNumber?.trim()?.toLong() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun parseInputDate(inputDate: String?): Int {
        return try {
            inputDate?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(
        name: String,
        numPhone: Long,
        data: Int
    ): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (numPhone <= 0) {
            _errorInputCount.value = true
            result = false
        }
        if (data <= 0) {
            _errorInputDate.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    fun resetErrorInputData() {
        _errorInputDate.value = false
    }
}