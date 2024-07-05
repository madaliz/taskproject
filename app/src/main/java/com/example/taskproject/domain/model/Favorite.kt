package com.example.taskproject.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorites", foreignKeys = [
    ForeignKey (entity = Persons::class , parentColumns = ["userId"], childColumns = ["userId"]),
    ForeignKey(entity = Food::class, parentColumns = ["foodId"], childColumns = ["foodId"])
]
)

data class Favorite(
    @PrimaryKey(autoGenerate = true) val favoriteId: Long,
    val userId: Long,
    val foodId: Long
)
