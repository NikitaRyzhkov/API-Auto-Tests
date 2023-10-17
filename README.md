# Автоматизация тестирования Todoist API

Тестовый фреймворк для автоматизированного тестирования функционала работы с заданиями и комментариями (Tasks, Comments) API веб-сервиса управления задачами Todoist.

Цель проекта - практическое применение, совершенствование и демонстрация навыков в области автоматизированного тестирования API.

## Структура

![image](https://github.com/NikitaRyzhkov/API-Auto-Tests/assets/121218999/a98420ec-6218-4faf-909e-0d037f03d841)

Выполняемые тесты содержатся в пакетах **comments**, **tasks**  в **src/test/java**

В папке src/main хранятся пакеты с вспомогательными элементами:

- **services** для храниния шаблонов запросов к API;
- **models** для хранения POJO объектов, используемых при сериализации тела запроса и десериализации тела ответа;
- **helpers** для создания сущностей и получения конкретных параметров этих сущностей, используемых в тестировании; для удаления всех сущностей после тестирования.


## Технологии
**REST Assured** для отправки запросов к API, выполнения ассертов и десериализации.

**TestNG** для управления тестами с помощью аннотаций и файла _testng.xml_, выполнения Asserts и Soft Asserts.

**Allure** для формирования наглядных отчетов

## Документация

Todoist API: https://developer.todoist.com/rest/v2/#overview

REST Assured: https://github.com/rest-assured/rest-assured/wiki/Usage

TestNG: https://testng.org/doc/documentation-main.html



