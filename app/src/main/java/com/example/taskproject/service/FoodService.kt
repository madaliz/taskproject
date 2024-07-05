package com.example.taskproject.service

import android.content.Context
import com.example.taskproject.data.datasource.AppDatabase
import com.example.taskproject.data.repository.FoodRepository
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite

class FoodService (context: Context){

    private  val foodRepository : FoodRepository

    init {
        val foodDao = AppDatabase.getDatabase(context).foodDao()
        foodRepository = FoodRepository(foodDao)
    }

    fun getFoodById(foodId: Long): Food {
        return foodRepository.getFoodById(foodId)
    }

    fun getFoods():List<Food>{
        return foodRepository.getFoods()
    }

    fun getFoodsWithFavorites(userId:Long):List<FoodWithFavorite>{
        return foodRepository.getFoodsWithFavorites(userId)
    }

    fun insertFood(food: Food) {
        foodRepository.insertFood(food)
    }


}