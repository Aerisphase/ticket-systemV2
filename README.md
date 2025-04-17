1. Настройка PostgreSQL
Запустите pgAdmin (устанавливается с PostgreSQL)

Создайте базу данных:

sql
Copy
CREATE DATABASE ticketdb;
CREATE USER ticketuser WITH PASSWORD 'ticketpass';
GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticketuser;
2. Настройка серверной части в VS Code
Откройте папку ticket-server в VS Code

Измените src/main/resources/application.properties:

properties
Copy
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=ticketuser
spring.datasource.password=ticketpass
Запустите сервер:

Откройте класс TicketServerApplication.java

Нажмите F5 или используйте меню "Run -> Start Debugging"

Или через терминал:

powershell
Copy
mvnw.cmd spring-boot:run
3. Настройка клиентской части
Откройте папку ticket-client в новом окне VS Code

Убедитесь, что файл pom.xml содержит все зависимости

Запустите клиент:

Откройте класс TicketClientApp.java

Нажмите F5 (предварительно создав конфигурацию запуска)

Или через терминал:

powershell
Copy
mvnw.cmd exec:java -Dexec.mainClass="com.ticketclient.TicketClientApp"
4. Альтернативный запуск (с package)
Соберите сервер:

powershell
Copy
cd ticket-server
mvnw.cmd clean package
java -jar target/ticket-server-0.0.1-SNAPSHOT.jar
Соберите клиент:

powershell
Copy
cd ticket-client
mvnw.cmd clean package
java -cp target/classes com.ticketclient.TicketClientApp


______________________________________________________
Postgres подробнее

1. Установка PostgreSQL
Скачайте установщик с официального сайта

Запустите установщик и следуйте шагам:

Укажите директорию установки (по умолчанию C:\Program Files\PostgreSQL\<версия>)

На шаге "Select Components" оставьте все компоненты отмеченными

Укажите директорию данных (по умолчанию C:\Program Files\PostgreSQL\<версия>\data)

Задайте пароль для суперпользователя postgres (запомните его!)

Порт оставьте по умолчанию (5432)

Локаль можно оставить "Default locale"

2. Настройка сервера
После установки найдите "pgAdmin 4" в меню Пуск и запустите

При первом запуске задайте мастер-пароль для pgAdmin

В левом меню:

Разверните "Servers" → "PostgreSQL <версия>"

Введите пароль, заданный при установке

3. Создание базы данных и пользователя
В pgAdmin:

Правой кнопкой на "Databases" → "Create" → "Database"

Введите имя: ticketdb

Владельца оставьте postgres → "Save"

Создайте пользователя:

Правой кнопкой на "Login/Group Roles" → "Create" → "Login/Group Role"

Во вкладке "General":

Имя: ticketuser

Во вкладке "Definition":

Пароль: ticketpass

Повторите пароль

Во вкладке "Privileges":

Включите "Can login?" и "Superuser?"

Нажмите "Save"

4. Настройка прав доступа
Найдите файл pg_hba.conf (обычно в C:\Program Files\PostgreSQL\<версия>\data)

Добавьте в конец файла:

Copy
# TYPE  DATABASE        USER            ADDRESS                 METHOD
host    ticketdb        ticketuser      127.0.0.1/32            md5
host    ticketdb        ticketuser      ::1/128                 md5
Найдите файл postgresql.conf в той же папке

Убедитесь, что строка содержит:

Copy
listen_addresses = '*'
5. Перезапуск сервера
Через панель управления:

Откройте "Службы" (Win+R → services.msc)

Найдите "postgresql-x64-<версия>"

Правой кнопкой → "Перезапустить"

6. Проверка подключения
В pgAdmin создайте новое подключение:

Host: localhost

Port: 5432

Maintenance database: postgres

Username: ticketuser

Password: ticketpass

Или через командную строку:

powershell
Copy
psql -U ticketuser -d ticketdb -h localhost
(введите пароль ticketpass)

7. Настройка для Spring Boot
В application.properties укажите:

properties
Copy
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=ticketuser
spring.datasource.password=ticketpass
spring.datasource.driver-class-name=org.postgresql.Driver

# Для автоматического создания таблиц
spring.jpa.hibernate.ddl-auto=update
8. Дополнительные настройки (опционально)
Для удаленного доступа:

В брандмауэре разрешите входящие подключения на порт 5432

В postgresql.conf укажите:

Copy
listen_addresses = '*'
В pg_hba.conf добавьте:

Copy
host    all             all             0.0.0.0/0               md5
Для резервного копирования:

powershell
Copy
pg_dump -U ticketuser -d ticketdb -f backup.sql
Для восстановления:

powershell
Copy
psql -U ticketuser -d ticketdb -f backup.sql
