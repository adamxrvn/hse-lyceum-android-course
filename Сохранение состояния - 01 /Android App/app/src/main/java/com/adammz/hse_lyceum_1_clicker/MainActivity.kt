package com.adammz.hse_lyceum_1_clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adammz.hse_lyceum_1_clicker.ui.theme.HSELyceum1ClickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HSELyceum1ClickerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {


/*                    Проблема:
                      При изменении конфигурации активность перезапускается,
                      следовательно все данные сбрасываются.

                      Примеры изменения конфигурации:
                      Изменение ориентации экрана - портретный/альбомный,
                      Изменение раскладки клавиатуры - появление/исчезновение экранной клавиатуры,
                      Изменение языка и региональных настроек - смена языка системы, форматов,
                      и т.д.

                      Решение:
                      Применение rememberSaveable, который ведет себя аналогично "remember",
                      но сохраненное значение переживет пересоздание активности или процесса
                            с помощью механизма сохранения состояния экземпляра

                      На следующих занятиях также рассмотреим ViewModel и Persistent Storage
*/


                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(
                                // Зальём фон градиентом
                                Brush.linearGradient(
                                    colors = listOf(Color.Black, Color(0xFFFAA300)),
                                    start = Offset.Zero,
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                )
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )


                    {

                        Clicker()


                    }


                }
            }
        }
    }
}

@Composable
fun Clicker() {

    // Создаем saveable счетчик
    // Изначальное значение задаем нулем
    var count by rememberSaveable {
        mutableStateOf(0)
    }


    // Сохраняем текущий сикн кликера
    var currentSkin by rememberSaveable {
        mutableStateOf(R.drawable.planet1)
    }

    // Добавим скины для кликера
    // Просто создаем пары: id картинки и стоимость скина
    // Можно было использовать отдельный класс, но для простоты возьмем список пар,
    // потому что для кастомных классов нужно писать отдельный Saver
    val skins = listOf(
        Pair(R.drawable.planet1, 0),
        Pair(R.drawable.planet2, 52),
        Pair(R.drawable.planet3, 104)
    )


    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Text(
            text = "$${count}",
            color = Color.White,
            fontSize = 52.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
        )

        Image(
            painter = painterResource(id = currentSkin),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                .clickable(
                    // Данные свойства позволяют убрать анимацию затемнения при клике
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    // Увеличиваем счетчик
                    count++
                }
        )
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Skins",
            fontWeight = FontWeight.Black,
            color = Color.White,
            fontSize = 52.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(3) {
                Image(
                    painter = painterResource(id = skins[it].first),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(10.dp)
                        .clickable {
                            // Позволяем менять скин, если пользователь достиг нужного количества кликов
                            if (skins[it].second <= count)
                                currentSkin = skins[it].first
                        }
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HSELyceum1ClickerTheme {
    }
}