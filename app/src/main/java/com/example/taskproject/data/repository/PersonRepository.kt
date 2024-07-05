package com.example.taskproject.data.repository

import com.example.taskproject.data.datasource.PersonDao
import com.example.taskproject.domain.model.Persons

class PersonRepository(private val personDao: PersonDao) {

    fun getPersonById(personId: Long): Persons? {
        return personDao.getPersonById(personId)
    }

    fun getAllPersons():List<Persons>{
        return personDao.getPersons()
    }

    suspend fun insertPerson(person: Persons): Long {
        return personDao.insertPerson(person)
    }
}