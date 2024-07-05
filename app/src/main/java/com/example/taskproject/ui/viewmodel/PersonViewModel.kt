package com.example.taskproject.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskproject.domain.model.Persons
import com.example.taskproject.service.PersonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PersonViewModel(application: Application):AndroidViewModel(application) {

    private val personService: PersonService = PersonService(application)

    fun getPersonById(personId: Long, callback: (Persons?) -> Unit) {
        viewModelScope.launch {
            val person = withContext(Dispatchers.IO) {
                personService.getPersonById(personId)
            }
            callback(person)
        }
    }

    fun getAllPerson(callback: (List<Persons>) -> Unit){
        viewModelScope.launch {
            val persons = withContext(Dispatchers.IO){
                personService.getAllPerson()
            }
            callback(persons)
        }


    }

    fun insertPerson(person: Persons): LiveData<Long> {
        val idLiveData = MutableLiveData<Long>()
        viewModelScope.launch {
            val id = personService.insertPerson(person)
            idLiveData.postValue(id)
        }
        return idLiveData
    }
}