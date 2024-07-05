package com.example.taskproject.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "persons")
data class Persons(
    @PrimaryKey(autoGenerate = true)
    val userId : Long,
    val username : String
)
