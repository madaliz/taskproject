package com.example.taskproject.service

import android.content.Context
import com.example.taskproject.data.datasource.AppDatabase
import com.example.taskproject.data.repository.PersonRepository
import com.example.taskproject.domain.model.Persons

class PersonService (context: Context) {

    private val personRepository: PersonRepository

    init {
        val personDao = AppDatabase.getDatabase(context).personDao()
        personRepository = PersonRepository(personDao)
    }

    fun getPersonById(userId: Long): Persons? {
        return personRepository.getPersonById(userId)
    }

    fun getAllPerson():List<Persons>{
        return  personRepository.getAllPersons()
    }

    suspend fun insertPerson(person: Persons): Long {
        return personRepository.insertPerson(person)
    }
}