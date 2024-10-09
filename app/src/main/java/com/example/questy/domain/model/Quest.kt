package com.example.questy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quest(
    val image: Int? = null,
    val title: String,
    val description: String = "",
    val btnQuest: String = "",
    var id: Int = UNDEFINED_ID
): Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}