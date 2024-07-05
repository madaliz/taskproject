package com.example.taskproject.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.FoodWithFavorite


@Dao
interface FoodDao {

    @Query("SELECT * FROM foods WHERE foodId = :foodId")
    fun getFoodById(foodId:Long):Food

    @Query("SELECT * FROM foods ")
    fun getFoods():List<Food>


    @Query("""
        SELECT f.foodId, f.name, 
        CASE WHEN fav.foodId IS NOT NULL THEN 1 ELSE 0 END AS isFavorite
        FROM foods f
        LEFT JOIN favorites fav ON f.foodId = fav.foodId AND fav.userId = :userId
    """)
    fun getFoodsWithFavorites(userId: Long): List<FoodWithFavorite>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: Food)

}