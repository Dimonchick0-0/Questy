package com.example.questy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val numberPhone: Long,
    val data: Int,
    val questTitle: Quest,
    var id: Int = UNDEFINED_ID
): Parcelable {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}