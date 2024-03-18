# Retrofit


Документация: https://hse-sandwich-77a114b9406f.herokuapp.com/docs

Endpoint: https://hse-sandwich-77a114b9406f.herokuapp.com/



#### 1. Добавляем зависимости в build.gradle.kts (Module :app)
```kotlin
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

```

#### 2. Добавляем доступ в интернет AndroidManifest.xml
```kotlin
    <uses-permission android:name="android.permission.INTERNET"/>
```

