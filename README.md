# Your-Dollar-Your-GIF API

An API service that accesses the exchange rate service, and displays a gif

# Getting started

You'll need Java 17 installed. Download and unzip the source repository, or clone it using Git\
`git clone https://github.com/MadMouth/Your-Dollar-Your-GIF.git`

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

