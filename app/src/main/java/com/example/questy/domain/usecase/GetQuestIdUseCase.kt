package com.example.questy.domain.usecase

import com.example.questy.domain.model.Quest
import com.example.questy.domain.repository.QuestRepository

class GetQuestIdUseCase(private val questRepository: QuestRepository) {
    operator fun invoke(questItemId: Int): Quest? {
        return questRepository.getQuestId(questItemId)
    }
}