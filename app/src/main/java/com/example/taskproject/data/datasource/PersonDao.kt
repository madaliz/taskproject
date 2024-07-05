package com.example.taskproject.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskproject.domain.model.Food
import com.example.taskproject.domain.model.Persons

@Dao
interface PersonDao {

    @Query("SELECT * FROM persons WHERE userId = :userId")
    fun getPersonById(userId:Long):Persons

    @Query("SELECT * FROM persons ")
    fun getPersons():List<Persons>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Persons): Long
}