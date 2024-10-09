package com.example.questy.data

import com.example.questy.R
import com.example.questy.domain.model.Quest

class Quests {
    fun questList(): MutableList<Quest> {
        return mutableListOf(
            Quest(
                R.drawable.homeprizrack,
                "Ночь с потусторонним",
                "Эта ночь может стать для вас последней...",
                "Узнать больше"

            ),
            Quest(
                R.drawable.pikovayadama,
                "Пиковая дама",
                "Знаменитая легенда детства появилась наяву...",
                "Узнать больше"
            ),
            Quest(
                R.drawable.annabel,
                "Проклятие аннабель",
                "Вы станете бояться кукол...",
                "Узнать больше"
            )
        )
    }
}