package com.example.questy.domain.usecase

import com.example.questy.domain.model.Quest
import com.example.questy.domain.repository.QuestRepository

class SetQuestListUseCase(private val questRepository: QuestRepository) {
     operator fun invoke(list: MutableList<Quest>) {
        questRepository.setQuestList(list)
    }
}