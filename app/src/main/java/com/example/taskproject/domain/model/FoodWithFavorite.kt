package com.example.taskproject.domain.model

data class FoodWithFavorite (
    val foodId: Long,
    val name: String,
    var isFavorite: Boolean
)