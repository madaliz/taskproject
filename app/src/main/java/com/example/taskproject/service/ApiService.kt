package com.example.taskproject.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        private const val BASE_URL = "https://api.example.com/"

        fun create(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

    interface ApiServiceInterface {

    }
}