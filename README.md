
# Система бронирования билетов (Java + PostgreSQL)

## Содержание
1. [Технологический стек](#технологический-стек)
2. [Установка и настройка](#установка-и-настройка)
3. [Запуск приложения](#запуск-приложения)
4. [Настройка PostgreSQL](#настройка-postgresql)
5. [Дополнительные настройки](#дополнительные-настройки)

---

## Технологический стек
- **Backend**: Java 17+, Spring Boot 3.1+, Hibernate 6.0+
- **Frontend**: Java Swing
- **База данных**: PostgreSQL 14+
- **Билд-система**: Maven 3.8+

---

## Установка и настройка

### Предварительные требования
- Установите [Java 17+](https://adoptium.net/)
- Установите [PostgreSQL 14+](https://www.postgresql.org/download/)
- Установите [Maven 3.8+](https://maven.apache.org/)

---

## Запуск приложения

### Серверная часть
```bash
cd ticket-server
mvn spring-boot:run
Клиентская часть
bash
Copy
cd ticket-client
mvn exec:java -Dexec.mainClass="com.ticketclient.TicketClientApp"
Настройка PostgreSQL
1. Создание БД и пользователя
sql
Copy
CREATE DATABASE ticketdb;
CREATE USER ticketuser WITH PASSWORD 'ticketpass';
GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticketuser;
2. Настройка подключения
В ticket-server/src/main/resources/application.properties:

properties
Copy
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=ticketuser
spring.datasource.password=ticketpass
spring.jpa.hibernate.ddl-auto=update
3. Настройка прав доступа
В файле pg_hba.conf добавьте:

Copy
host    ticketdb        ticketuser      127.0.0.1/32            md5
host    ticketdb        ticketuser      ::1/128                 md5
Дополнительные настройки
Удаленный доступ
В postgresql.conf:

ini
Copy
listen_addresses = '*'
В pg_hba.conf:

Copy
host    all             all             0.0.0.0/0               md5
Резервное копирование
bash
Copy
pg_dump -U ticketuser -d ticketdb -f backup.sql
Восстановление
bash
Copy
psql -U ticketuser -d ticketdb -f backup.sql
Примечание: Для работы с pgAdmin используйте мастер-пароль, заданный при установке PostgreSQL.
