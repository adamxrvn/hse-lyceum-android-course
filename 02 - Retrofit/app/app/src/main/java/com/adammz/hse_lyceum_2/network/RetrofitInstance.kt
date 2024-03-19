package com.adammz.hse_lyceum_2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Объект RetrofitInstance для создания единственного экземпляра Retrofit
object RetrofitInstance {

    // Приватная константа, хранящая базовый URL-адрес сервера
    private const val BASE_URL = "https://hse-sandwich-a47b9a61e68b.herokuapp.com/"

    // Ленивое создание экземпляра ApiService
    // Этот блок кода выполнится только при первом обращении к переменной api
    val api: ApiService by lazy {
        // Создание объекта Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Установка базового адреса сервера
            .addConverterFactory(GsonConverterFactory.create()) // Добавление конвертера из JSON в объекты Kotlin
            .build() // Построение объекта Retrofit
        // Создание реализации ApiService из объекта Retrofit
        retrofit.create(ApiService::class.java)
    }
}