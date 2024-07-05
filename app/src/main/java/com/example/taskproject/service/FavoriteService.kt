package com.example.taskproject.service

import android.content.Context
import com.example.taskproject.data.datasource.AppDatabase
import com.example.taskproject.data.repository.FavoriteRepository
import com.example.taskproject.domain.model.Favorite
import com.example.taskproject.domain.model.Food

class FavoriteService(context: Context) {

    private  val favoriteRepository : FavoriteRepository

    init {
        val  favoriteDao = AppDatabase.getDatabase(context).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
    }

    fun insertFavorite(favorite: Favorite) {
        favoriteRepository.insertFavorite(favorite)
    }

    fun deleteFavorite(personId: Long, foodId: Long) {
        favoriteRepository.deleteFavorite(personId, foodId)
    }
}