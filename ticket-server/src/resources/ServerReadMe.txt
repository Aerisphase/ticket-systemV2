В файле application.properties настройки пользователя и пароля для доступа к PostgreSQL задаются следующим образом:

1. Основные параметры подключения:
properties
Copy
# URL для подключения к БД (замените 'ticketdb' на имя вашей БД)
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb

# Имя пользователя PostgreSQL (по умолчанию 'postgres')
spring.datasource.username=postgres

# Пароль пользователя (укажите ваш реальный пароль)
spring.datasource.password=your_actual_password_here

# Драйвер БД
spring.datasource.driver-class-name=org.postgresql.Driver
2. Полный пример файла:
properties
Copy
# Настройки сервера
server.port=8080

# Настройки PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.datasource.driver-class-name=org.postgresql.Driver

# Настройки Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
3. Как создать пользователя в PostgreSQL:
Подключитесь к PostgreSQL через psql:

bash
Copy
psql -U postgres
Создайте нового пользователя (если нужно):

sql
Copy
CREATE USER ticket_user WITH PASSWORD 'user_password';
Создайте БД и дайте права пользователю:

sql
Copy
CREATE DATABASE ticketdb;
GRANT ALL PRIVILEGES ON DATABASE ticketdb TO ticket_user;
Тогда настройки будут:

properties
Copy
spring.datasource.username=ticket_user
spring.datasource.password=user_password
4. Важные моменты безопасности:
Никогда не коммитьте реальные пароли в Git:

Добавьте application.properties в .gitignore

Или используйте environment variables:

properties
Copy
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
И задавайте переменные при запуске:

bash
Copy
export DB_USER=postgres
export DB_PASS=secret
java -jar your-app.jar
Для production используйте:

Шифрование паролей

Подключение через Vault

Kubernetes Secrets

5. Проверка подключения:
Если возникли ошибки подключения, проверьте:

Запущен ли сервер PostgreSQL:

bash
Copy
sudo systemctl status postgresql
Доступность порта 5432:

bash
Copy
netstat -tulnp | grep 5432
Правильность пароля:

bash
Copy
psql -U your_username -d ticketdb -W
Для тестового окружения можно использовать H2 (встроенную БД):

properties
Copy
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
