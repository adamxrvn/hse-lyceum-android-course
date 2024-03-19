package com.adammz.hse_lyceum_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.adammz.hse_lyceum_2.ui.theme.HSELyceum2Theme
import com.adammz.hse_lyceum_2.viewmodel.SandwichViewModel


class MainActivity : ComponentActivity() {

    // Инициализация ViewModel для работы с данными о сэндвичах
    private val viewModel: SandwichViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HSELyceum2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Выводит список сэндвичей, используя данные из ViewModel
                    SandwichList(viewModel = viewModel)
                }
            }
        }
    }

    @Composable
    fun SandwichList(viewModel: SandwichViewModel) {
        // Подписка на изменения списка сэндвичей в ViewModel
        val sandwiches by viewModel.sandwiches
        // Создание вертикального списка
        LazyColumn {
            // Проходит по списку сэндвичей и создает для каждого элемента UI
            items(sandwiches) { sandwich ->
                // Строка для размещения изображения и текста сэндвича
                Row(
                    modifier = Modifier.fillMaxWidth(), // Занимает максимальную ширину
                    verticalAlignment = Alignment.CenterVertically // Вертикальное выравнивание элементов
                ) {
                    // Вывод изображения сэндвича
                    Image(
                        painter = rememberAsyncImagePainter(sandwich.icon), // Загрузка изображения
                        contentDescription = null, // Описание контента (для доступности)
                        modifier = Modifier
                            .size(82.dp) // Размер изображения
                            .padding(8.dp) // Отступы вокруг изображения
                            .clip(RoundedCornerShape(8.dp)) // Скругленные углы изображения
                    )
                    // Колонка для названия и цены сэндвича
                    Column {
                        // Название сэндвича
                        Text(
                            text = sandwich.name, // Текстовое содержимое
                            fontSize = 26.sp, // Размер шрифта
                            modifier = Modifier.padding(8.dp, 0.dp) // Отступы
                        )
                        // Цена сэндвича
                        Text(
                            text = "${sandwich.price} р.", // Текстовое содержимое
                            fontSize = 18.sp, // Размер шрифта
                            modifier = Modifier.padding(8.dp, 0.dp) // Отступы
                        )
                    }
                }
                // Разделитель между элементами списка
                Divider()
            }
        }
        // Эффект для однократного выполнения действий при первом рендеринге компонента
        DisposableEffect(Unit) {
            viewModel.getSandwiches() // Запрос списка сэндвичей у ViewModel
            onDispose {} // Операции при удалении компонента из дерева композиции
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HSELyceum2Theme {
    }
}