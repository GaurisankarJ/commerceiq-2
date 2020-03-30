# Spring Boot Server for API calls to mySQL Database [LOCALHOST:8080]

# API's (Swagger Documentation Available)

> You can access both the API's on Swagger
```
http://localhost:8080/swagger-ui.html
```

## Run

```
// Install mySQL server
// Install Java 1.8 SDK and Maven 3.5.4
// Run mySQL server
// To run server
 mvn spring-boot:run
```

## APIs Flow

> Create Theater POST /theaters/add, get back theaterId

> Create Movie POST /movies/add, use theaterId to create movie with movieId returned

> Get Cities GET /cities, get back cities

> Get Movies GET /movies, use city as parameter

> Get Theaters GET /theater/find, use movieName and city as parameter, get theaterId returned

> Get Shows GET /shows, use city, movieName and theaterId as parameter, get movieId returned along with shows [1 means not booked]

> Book Show POST /book, use movieId, theaterId, name, phoneNumber, seats[comma seperated string], show[morning, noon, night]
	


