# Retrofit


Документация: https://hse-sandwich-a47b9a61e68b.herokuapp.com/docs

Endpoint: https://hse-sandwich-a47b9a61e68b.herokuapp.com/



#### 1. Добавляем зависимости в `build.gradle.kts (Module :app)`
```kotlin
    // Для REST API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Для подгрузки картинок с сервера
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")
```

#### 2. Добавляем доступ в интернет `AndroidManifest.xml`
```kotlin
<uses-permission android:name="android.permission.INTERNET"/>
```


