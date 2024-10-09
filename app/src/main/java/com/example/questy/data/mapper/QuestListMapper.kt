package com.example.questy.data.mapper

import com.example.questy.data.db.UserBookingQuestDatabase
import com.example.questy.domain.model.User

class QuestListMapper {
    private fun mapDbModelToEntity(userBookingQuestDatabase: UserBookingQuestDatabase) = User(
        name = userBookingQuestDatabase.name,
        numberPhone = userBookingQuestDatabase.numberPhone,
        data = userBookingQuestDatabase.data,
        id = userBookingQuestDatabase.id,
        questTitle = userBookingQuestDatabase.questTitle
    )
    fun mapEntityToDbModel(user: User) = UserBookingQuestDatabase(
        id = user.id,
        name = user.name,
        numberPhone = user.numberPhone,
        data = user.data,
        questTitle = user.questTitle
    )
    fun mapListDbModelToListEntiy(list: List<UserBookingQuestDatabase>) = list.map {
        mapDbModelToEntity(it)
    }
}