package com.example.taskproject.data.repository

import com.example.taskproject.data.datasource.FoodDao
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite

class FoodRepository(val foodDao: FoodDao) {

    fun getFoodById(foodId: Long): Food {
        return foodDao.getFoodById(foodId)
    }

    fun getFoods(): List<Food> {
        return foodDao.getFoods()
    }

    fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }

    fun getFoodsWithFavorites(userId:Long):List<FoodWithFavorite>{
        return foodDao.getFoodsWithFavorites(userId)
    }
}