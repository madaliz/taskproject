package com.example.taskproject.data.repository

import com.example.taskproject.data.datasource.FavoriteDao
import com.example.taskproject.domain.model.Favorite
import com.example.taskproject.domain.model.Food

class FavoriteRepository(val favoriteDao: FavoriteDao) {


    fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    fun deleteFavorite(userId: Long, foodId: Long) {
        favoriteDao.deleteFavorite(userId, foodId)
    }

}