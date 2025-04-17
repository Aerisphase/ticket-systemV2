# Ticket Reservation System 🎟️

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1+-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-blue)
![Maven](https://img.shields.io/badge/Maven-3.8+-yellow)

Клиент-серверная система бронирования билетов с графическим интерфейсом на Java Swing и REST API на Spring Boot.

## 📋 Содержание
- [Особенности](#-особенности)
- [Технологический стек](#-технологический-стек)
- [Установка](#-установка)
- [Запуск](#-запуск)
- [Использование](#-использование)
- [API Документация](#-api-документация)
- [Лицензия](#-лицензия)

## ✨ Особенности
- Полноценный графический интерфейс на Swing
- REST API для управления событиями и бронированиями
- Интеграция с PostgreSQL
- Поддержка транзакций
- Валидация данных

## 🛠 Технологический стек
### Серверная часть
- Java 17
- Spring Boot 3.1
- Spring Data JPA
- Hibernate 6.0
- PostgreSQL 14
- Lombok
- ModelMapper

### Клиентская часть
- Java 17
- Swing
- HttpClient
- Jackson

## 📥 Установка
1. Клонируйте репозиторий:
```bash
git clone https://github.com/your-repo/ticket-reservation-system.git
Установите PostgreSQL и создайте БД:

sql
Copy
CREATE DATABASE ticketdb;
CREATE USER ticketuser WITH PASSWORD 'ticketpass';
GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticketuser;
Настройте подключение к БД в файле:
ticket-server/src/main/resources/application.properties

🚀 Запуск
Сервер
bash
Copy
cd ticket-server
mvn spring-boot:run
Клиент
bash
Copy
cd ticket-client
mvn exec:java -Dexec.mainClass="com.ticketclient.TicketClientApp"
🖥 Использование
Запустите сервер и клиент

В клиентском приложении:

Просматривайте список мероприятий

Выбирайте мероприятие для просмотра деталей

Нажимайте "Забронировать" для создания брони

Вводите свои данные (имя и email)

📡 API Документация
Сервер предоставляет REST API по адресу http://localhost:8080/api

Мероприятия
GET /api/events - список всех мероприятий

POST /api/events - создать новое мероприятие

Билеты
POST /api/tickets - забронировать билет

PUT /api/tickets/{id}/pay - оплатить билет

📜 Лицензия
MIT License. Подробнее см. в файле LICENSE.

### 📌 Инструкция по запуску проекта "Система бронирования билетов"

#### 1. Подготовка среды
**Необходимые компоненты:**
- [Java JDK 17+](https://adoptium.net/temurin/releases/)
- [PostgreSQL 14+](https://www.postgresql.org/download/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

#### 2. Установка PostgreSQL
1. Запустите установщик PostgreSQL
2. Запомните пароль для пользователя `postgres`
3. После установки откройте **pgAdmin**
4. Создайте новую базу данных:
   ```sql
   CREATE DATABASE ticketdb;
   CREATE USER ticketuser WITH PASSWORD 'ticketpass';
   GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticketuser;
   ```

#### 3. Настройка серверной части
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/your-repo/ticket-reservation-system.git
   cd ticket-reservation-system
   ```

2. Настройте подключение к БД (файл `ticket-server/src/main/resources/application.properties`):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
   spring.datasource.username=ticketuser
   spring.datasource.password=ticketpass
   ```

3. Запустите сервер:
   ```bash
   cd ticket-server
   mvn spring-boot:run
   ```
   Сервер будет доступен по адресу: `http://localhost:8080`

#### 4. Запуск клиентского приложения
1. В новом терминале:
   ```bash
   cd ticket-client
   mvn exec:java -Dexec.mainClass="com.ticketclient.TicketClientApp"
   ```

2. Или через IDE:
   - Откройте класс `TicketClientApp.java`
   - Нажмите "Run"

#### 5. Проверка работы
1. В клиентском приложении:
   - Должен отобразиться список мероприятий
   - Можно забронировать билет, нажав кнопку "Забронировать"

2. Для проверки API:
   ```bash
   curl http://localhost:8080/api/events
   ```

#### Альтернативный запуск (сборка JAR)
**Сервер:**
```bash
cd ticket-server
mvn clean package
java -jar target/ticket-server-0.0.1-SNAPSHOT.jar
```

**Клиент:**
```bash
cd ticket-client
mvn clean package
java -cp target/classes com.ticketclient.TicketClientApp
```

#### 🔍 Устранение неполадок
1. Если PostgreSQL не подключается:
   - Проверьте, что служба PostgreSQL запущена
   - Убедитесь, что в `pg_hba.conf` есть строка:
     ```
     host    ticketdb        ticketuser      127.0.0.1/32            md5
     ```

2. Если клиент не видит сервер:
   - Проверьте, что сервер запущен (`http://localhost:8080/actuator/health`)
   - Убедитесь, что брандмауэр не блокирует порт 8080

> **Примечание:** Для первого запуска может потребоваться несколько минут на загрузку зависимостей Maven.
