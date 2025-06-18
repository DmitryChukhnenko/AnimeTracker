# AnimeTracker

Приложение для отслеживания просмотренных/прочитанных аниме, манги, ранобе и других произведений. Реализовано с использованием современных практик Android-разработки и Clean Architecture.

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## Основные особенности
- 🔍 Поиск аниме/манги через [Jikan API v4](https://jikan.moe/)
- 💾 Локальное хранение данных через Room Database
- 🔄 Чистая архитектура с MVVM подходом
- ⚡ Асинхронные операции через Kotlin Coroutines
- 🖼 Загрузка и кэширование изображений через Glide
- 🧠 DI через Koin
- 🌐 Сетевое взаимодействие через Retrofit + OkHttp

## Технологии и библиотеки
| Категория | Использованные технологии |
|------------------|--------------------------------------------|
| Архитектура | MVVM, Clean Architecture |
| DI | Koin 3.4 |
| База данных | Room 2.6 |
| Сеть | Retrofit 2.9, OkHttp 4.12 |
| Изображения | Glide 4.16 |
| Навигация | Jetpack Navigation + BottomNavigationView |
| Асинхронность | Kotlin Coroutines 1.7 |
| UI | Material Design 3, RecyclerView |

## Архитектура проекта (Clean Architecture)
```
app/
├── data/ # Работа с данными (API + Room)
├── domain/ # Бизнес-логика и интерфейсы
├── di/ # Dependency Injection
└── presentation/ # UI слой (фрагменты, вьюмодели)
```

## Как запустить проект
1. Клонируйте репозиторий:
```bash
git clone https://github.com/DmitryChukhnenko/AnimeTracker.git
```
2. Откройте проект в Android Studio (Android Studio Giraffe или выше)
3. Дождитесь синхронизации Gradle
4. Подключите устройство или создайте эмулятор
5. Запустите приложение через Run > Run 'app'

> ⚠️ Для работы с API требуется подключение к интернету

## Структура кода
```
app/
├── data/
│ ├── local/ # Room DAO, Entity, Database
│ ├── remote/ # API интерфейсы и DTO
│ └── repository/ # Реализация репозиториев
├── domain/
│ ├── model/ # Доменные модели
│ ├── repository/ # Интерфейсы репозиториев
│ └── use_case/ # Use Cases
├── presentation/
│ ├── common/ # Общие UI-компоненты
│ ├── search/ # Экран поиска
│ ├── list/ # Экран списка
│ └── detail/ # Экран деталей
└── di/ # Koin модули
```

## Вклад
Пулл-реквесты приветствуются! Для больших изменений откройте issue сначала, чтобы обсудить изменения.

---

© 2025 Anime Tracker App. Все права защищены. 
Для получения информации об API: [Jikan Documentation](https://docs.api.jikan.moe/)

