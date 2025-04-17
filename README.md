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

🗃 Настройка базы данных
Установите PostgreSQL

Создайте БД и пользователя:

sql
Copy
CREATE DATABASE ticketdb;
CREATE USER ticketuser WITH PASSWORD 'ticketpass';
GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticketuser;
Настройте подключение в ticket-server/src/main/resources/application.properties:

properties
Copy
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=ticketuser
spring.datasource.password=ticketpass
🚀 Запуск сервера
bash
Copy
cd ticket-server
mvn spring-boot:run
Сервер будет доступен на http://localhost:5432

💻 Запуск клиента
bash
Copy
cd ticket-client
mvn exec:java -Dexec.mainClass="com.ticketclient.TicketClientApp"
✔️ Проверка работы
В клиентском приложении должен появиться список мероприятий

Для проверки API выполните:

bash
Copy
curl http://localhost:5432/api/events
🔧 Устранение неполадок
Ошибки подключения к PostgreSQL
Проверьте статус службы PostgreSQL

Убедитесь что в pg_hba.conf есть:

Copy
host    ticketdb        ticketuser      127.0.0.1/32            md5
Клиент не подключается к серверу
Проверьте что сервер запущен:

bash
Copy
curl http://localhost:5432/actuator/health
Убедитесь что порт 5432 не заблокирован брандмауэром
