package com.example.taskproject.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite
import com.example.taskproject.service.FoodService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodViewModel(application: Application):AndroidViewModel(application) {

    private val foodService: FoodService = FoodService(application)

    fun getFoodById(foodId: Long, callback: (Food?) -> Unit) {
        viewModelScope.launch {
            val food = withContext(Dispatchers.IO) {
                foodService.getFoodById(foodId)
            }
            callback(food)
        }
    }

    fun getFoods(callback: (List<Food>) -> Unit){
        viewModelScope.launch {
            val foods = withContext(Dispatchers.IO){
                foodService.getFoods()
            }
            callback(foods)
        }

    }

    fun getFoodsWithFavorites(userId:Long,callback: (List<FoodWithFavorite>)-> Unit){
        viewModelScope.launch {
            val foods = withContext(Dispatchers.IO){
                foodService.getFoodsWithFavorites(userId)
            }
            callback(foods)
        }
    }
    fun insertFood(food: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            foodService.insertFood(food)
        }
    }
}