package com.example.taskproject.data.datasource

import androidx.room.*
import com.example.taskproject.domain.model.Favorite
import com.example.taskproject.domain.model.Food


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE foodId = :foodId AND userId = :userId")
    fun deleteFavorite(userId: Long,foodId:Long)


}