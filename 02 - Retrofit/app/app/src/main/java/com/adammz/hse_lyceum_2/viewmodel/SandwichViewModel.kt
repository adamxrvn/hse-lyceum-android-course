package com.adammz.hse_lyceum_2.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammz.hse_lyceum_2.model.Sandwich
import com.adammz.hse_lyceum_2.network.RetrofitInstance
import kotlinx.coroutines.launch

// Определение класса ViewModel для работы с данными сэндвичей
class SandwichViewModel : ViewModel() {
    // Получение экземпляра ApiService через синглтон RetrofitInstance для выполнения сетевых запросов
    private val apiService = RetrofitInstance.api

    // Состояние списка сэндвичей, обёрнутое в MutableState для наблюдения изменений в Jetpack Compose
    val sandwiches: MutableState<List<Sandwich>> = mutableStateOf(emptyList())

    // Функция для получения списка сэндвичей с сервера
    fun getSandwiches() {
        // Запуск корутины в области видимости ViewModel
        viewModelScope.launch {
            try {
                // Попытка получить список сэндвичей через сетевой запрос
                val response = apiService.getSandwiches()
                // Проверка, что полученный ответ не пустой
                if (response.isNotEmpty()) {
                    // Обновление состояния списка сэндвичей, если данные получены
                    sandwiches.value = response
                }
            } catch (e: Exception) {
                // Обработка ошибок сетевого запроса или преобразования данных
                // Здесь можно добавить логирование или показать пользовательское сообщение об ошибке
            }
        }
    }
}
