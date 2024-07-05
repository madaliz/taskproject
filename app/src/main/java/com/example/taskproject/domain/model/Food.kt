package com.example.taskproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val foodId : Long,
    val name : String
)
