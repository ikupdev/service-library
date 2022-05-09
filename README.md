# Приложение "Библиотека"

## Назначение
Приложение предназначено для организации удобного поиска, хранения и пользования электронными книгами, предоставляемых Google Book API.
Приложение обеспечивает следующие возможности:
1. Регистрация, аутентификация и авторизация пользователя.
2. Создание и управление "книжными полками" (книжная полка - подборка понравившихся и классифицируемых по определенному пользователем признаку электронных книг).
3. Поиск в базе данных Google Book API по ключевым словам (название, описание, автор, издательство и т.д.) и различным параметрам (платная/бесплатная книга, с возможностью просмотра текста книги частично или полностью).
4. В зависимости от найденной книги, имеется возможность перехода на страницы онлайн чтения книги, скачивания электронной книги в формате epub/pdf, либо перехода в онлайн-магазин для покупки. 
4. Сохранение данных книги в рамках книжной полки пользователя (пользователь получает возможность в любой момент времени производить просмотр данных добавленных книг). 
5. Данные книг, добавленных в базу данных приложения, обновляются ежедневно (производится синхронизация с Google Book API) для поддержания актуальности данных и ссылок. 
 
## Функционал и состав проекта
1. REST-контроллеры, обеспечивающие CRUD-операции для пользователей, книжных полок и книг, а также для поиска данных книг в Google Book API. 
2. Регистрация пользователя и обновление данных пользователя, аутентификация и авторизация пользователя на основе jwt-токена.
3. Сервисный слой, содержащий абстракции, отделяющие бизнес-логику приложения. 
4. Планировщик, выполняющий удаление "бесхозных" книг (не связанных ни с одной книжной полкой). 
5. Планировщик, выполняющий ежедневную синхронизацию данных книг с Google Book API. 
6. Поддерживается версионирование базы данных с помощью Liquibase.
7. Написаны интеграционные тесты, в процессе выполнения которых используется docker-контейнер с Postgresql 13.

Проект компилируется и запускается под JDK11.


## Запуск приложения с помощью docker-compose
Приложение настроено для запуска с базой данных postgresql 13 с помощью docker-compose. 
Для запуска проекта необходимо в корне проекта произвести сборку командой:
```
mvn clean package
```
Далее выполнить команду для запуска:
```
docker-compose up -d
```
После запуска контейнеров приложения REST API доступен по адресу: http://localhost:8086/service-library/

Документация Swagger доступна по адресу: http://localhost:8086/service-library/swagger-ui.html#

Адрес базы данных: jdbc:postgresql://localhost:5435/library

При первом запуске приложения доступен пользователь с логином kuper и паролем abcd1234.

## Установка и настройка

- Копируем скомпилированный модуль ***service-library/target/service-library.jar*** на площадку. Запускаем как Spring Boot модуль
(вручную или предварительно сконфигурировав соотв. службу).
Пример строки запуска (включая параметры для удаленной отладки):
```
/opt/jdk/bin/java -Xmx1024m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8080 -DenableRemoteDebugging=true -jar /opt/app/library-platform/service-library.jar --spring.config.additional-location=/opt/conf/,/opt/conf/service-library/
```
- При необходимости кастомизации настроек, копируем файл из состава проекта *application.yml* во внешнюю папку, указанную в строке
запуска (напр., в */opt/conf/service-library/*) и редактируем внешний файл. Для применения измененных настроек нужно рестартовать приложение.

- В *application.yml* содержатся параметры: стандартные для Spring Boot приложения, специфичные для подключенных модулей (Hibernate, Liquibase),
а также специфичные для данного приложения. Специфичные для приложения параметры содержатся в разделе *library*, напр.:
```
library:
  schema-name: com
  jwt:
    token:
      expired: 3600000
      secret: ikupdevsecret
  # not defined - removing turned off
  task:
    ## not defined - removing turned off
    remove-orphan-books:
      cron: 0 0 0 * * * #every day at 00:00 am
      ## not defined - sync turned off
    sync-google-book-api:
      cron: 0 0 1 * * * #every day at 01:00 am
      batch-size: 10 #sync book batch size
google:
  api:
    key: AIzaSyDIo47O3Sy9s4zOfyXvL_zSRxpMf2XeD3s
    url: https://www.googleapis.com/books/v1/
```
Отсутствие настройки *library.task.remove-orphan-books.cron* или *library.task.sync-google-book-api.cron* отключает соответствующие планировщики. 
*library.task.sync-google-book-api.batch-size* задает размер порции книг для выборки из БД при синхронизации данных книг c Google Book API по планировщику. 

С площадки должна быть доступна БД PostgreSQL, параметры которой (хост, порт, схема, пользователь) задаются в *application.yml*. 
Необходимая структура в БД разворачивается автоматически при старте приложения, согласно настройкам LiquiBase в приложении и в *application.yml*.

REST API приложения документирован с помощью Swagger:
```
http://HOST:PORT/service-library/swagger-ui.html#/
```
