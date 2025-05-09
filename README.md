## Архитектура проекта

<pre>
+------------------+
|      :core       |
|    (Use Cases)   |
+------------------+
        |
        v
+------------------+
|    :domain       |
| (Domain Layer)   |
|                  |
|   Repository,    |
|     Domain       |
+------------------+
        ^ |
        | v
+------------------+
|      :data       |
| (Data Layer)     |
| +--------------+ |
|       Dto,       |
|      Mapper,     |
|     Service,     |
|  RepositoryImpl, |
|  RetrofitClient  |
+------------------+
        |
        v
[ External API ]
</pre>

### Объекты

| Объект                        | Описание                                                                                     |
|-------------------------------|---------------------------------------------------------------------------------------------|
| **:core (Use Cases)**         | Слой бизнес-логики. Содержит UseCase'ы (например, `UserUseCase`, `AuthUseCase`).           |
| **:domain (Domain Layer)**    |                                                                                             |
| - Domain                      | Доменные модели (например, `UserDomain`, `AuthDomain`) — сущности предметной области.      |
| - Repository                  | Интерфейсы репозиториев (например, `UserRepository`) — методы для работы с данными.        |
| **:data (Data Layer)**        |                                                                                             |
| - Dto                         | Data Transfer Objects (например, `UserDto`) — структуры для маппинга данных из API.        |
| - Mapper                      | Мапперы (например, `UserMapper`) — преобразуют DTO в доменные модели и обратно.           |
| - Service                     | Интерфейсы для Retrofit (например, `UserService`) — определяют API-запросы.               |
| - RepositoryImpl              | Реализации репозиториев (например, `UserRepositoryImpl`) — используют сервисы и мапперы.   |
| - RetrofitClient              | Сетевой клиент для взаимодействия с внешним API.                                          |
| **External API**              | Внешний источник данных, с которым взаимодействует `:data` через `RetrofitClient`.         |
| **^ \| v (двойная стрелка)** | `:data` зависит от `:domain` (маппинг), поток вызовов от `:domain` к `:data` (репозитории). |