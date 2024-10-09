package com.example.questy.domain.usecase

import androidx.lifecycle.LiveData
import com.example.questy.domain.model.Quest
import com.example.questy.domain.repository.QuestRepository

class GetListQuestUseCase(private val questRepository: QuestRepository) {
    operator fun invoke(): LiveData<List<Quest>> {
        return questRepository.getQuestList()
    }
}