#spring-cloud sample

## feature list
1. service registry
2. service discovery (client-side)
3. load balancing (client-side)
4. centralized configuration
5. refresh configuration without restart
6. circuit breaker
7. api-gateway
8. filter


## some urls

### Eureka Server:
GET http://localhost:8761/

### Login test:
POST http://localhost:8080/api/session?userName=zhengzhiqing&password=111111
POST http://localhost:8080/session?userName=zhengzhiqing&password=111111

### product test:
GET http://localhost:8080/api/product/detail?productId=123456
GET http://localhost:8080/product/info?productId=123456
GET http://localhost:8080/product/price?productId=123456
GET http://localhost:8080/product/review?productId=123456

### Login test:
GET http://localhost:8888/api-gateway/default
GET http://localhost:8080/message
POST http://localhost:8080/refresh