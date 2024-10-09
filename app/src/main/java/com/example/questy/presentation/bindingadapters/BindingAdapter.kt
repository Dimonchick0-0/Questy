package com.example.questy.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import com.example.questy.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val msg = if (isError) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = msg
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val msg = if (isError) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = msg
}

@BindingAdapter("errorInputDate")
fun bindErrorInputDate(textInputLayout: TextInputLayout, isError: Boolean) {
    val msg = if (isError) {
        textInputLayout.context.getString(R.string.error_input_date)
    } else {
        null
    }
    textInputLayout.error = msg
}

@BindingAdapter("errorInputQuestTitle")
fun bindErrorInputQuestTitle(textInputLayout: TextInputLayout, isError: Boolean) {
    val msg = if (isError) {
        textInputLayout.context.getString(R.string.error_input_quest_title)
    } else {
        null
    }
    textInputLayout.error = msg
}