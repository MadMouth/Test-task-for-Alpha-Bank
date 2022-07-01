# Test task for Alpha-Bank

# Task
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
если ниже - отсюда https://giphy.com/search/broke
Ссылки
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
Must Have
Сервис на Spring Boot 2 + Java / Kotlin
Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
Для взаимодействия с внешними сервисами используется Feign
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
Для сборки должен использоваться Gradle
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
Nice to Have
Сборка и запуск Docker контейнера с этим сервисом

# Getting started

You'll need Java 17 installed. Download and unzip the source repository, or clone it using Git\
`git clone https://github.com/MadMouth/Test-task-for-company-A-.git`

Before launching, change in `application.properties` APP_ID and APP_KEY on your own values. Get
the [APP_ID](https://docs.openexchangerates.org/)
and [APP_KEY](https://developers.giphy.com/docs/api/#quick-start-guide) .

Then use the `build` and `run` task\
`./gradlew build` \
`./gradlew run`

To check if it works, open a browser tab at:\
_http://localhost:8080/api/v1/have-i-become-richer-today/default-currency_ \
The default currency is RUB. You can change this in `application.properties`

You can also specify the currency:\
_http://localhost:8080/api/v1/have-i-become-richer-today?symbol=AUD_

# Try it out with Docker

You'll need Docker installed. \
`./gradlew build`  - [change to docker multistage]\
`docker build -t yourdollaryourgif .` \
`docker run --name yourdollaryourgif -p 8080:8080 -e APP_ID=your_id -e APP_KEY=your_key yourdollaryourgif`

