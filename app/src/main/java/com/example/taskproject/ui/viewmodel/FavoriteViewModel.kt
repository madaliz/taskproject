package com.example.taskproject.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskproject.domain.model.Favorite
import com.example.taskproject.domain.model.Food
import com.example.taskproject.service.FavoriteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(application: Application):AndroidViewModel(application) {

    private val favoriteService: FavoriteService = FavoriteService(application)


    fun addFavorite(userId: Long, foodId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteService.insertFavorite(Favorite(0, userId, foodId))
        }
    }

    fun removeFavorite(userId: Long, foodId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteService.deleteFavorite(userId, foodId)
        }
    }
}