### spring-cloud sample
#### feature list
1. service registry
2. service discovery (client-side)
3. load balancing (client-side)
4. centralized configuration
5. refresh configuration without restart
6. circuit breaker
7. api-gateway
8. filter

#### Eureka Server:
cd eureka-server

./gradlew bootRun

http://localhost:8761/

#### Login test:
cd passport-service

./gradlew bootRun

##### access passport-service directly
curl -d "userName=zhengzhiqing&password=111111" http://localhost:9002/session

##### access passport-service via api-gateway
curl -d "userName=zhengzhiqing&password=111111" http://localhost:8080/api/ribbon/session


#### product test:
##### direct access

http://localhost:9003/product/1/price

http://localhost:9004/product/1/info

http://localhost:9005/product/1/review

##### via api gateway
http://localhost:8080/product/1/price

http://localhost:8080/product/1/info

http://localhost:8080/product/1/review

###### get item detail via api gateway
http://localhost:8080/api/ribbon/item/1/detail

http://localhost:8080/api/feign/item/1/detail

#### get configiration

http://localhost:8888/api-gateway/prod/master

http://localhost:8080/message

##### actuator

```
management:
  endpoints:
    web:
      exposure:
        include: "*"
```


we can include needed endpoints such as health, info, hystrix.stream etc.

http://localhost:8080/actuator

##### dashboard
http://localhost:10000/hystrix

http://localhost:8080/actuator/hystrix.stream

##### turbine
http://localhost:9999/turbine.stream?cluster=API-GATEWAY

##### timeout & retry &hystrix


```
zuul:
  retryable: true

feign:
  hystrix:
    enabled: true

ribbon:
  ConnectTimeout: 500
  ReadTimeout: 1500
  OkToRetryOnAllOperation: true
  MaxAutoRetriesNextServer: 1
  MaxAutoRetries: 1

hystrix:
  command:
    default:
      execution:
        timeout:
          enabled: true
        isolation:
          thread:
            timeoutInMilliseconds: 8050
```


hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds >
(ribbon.ConnectTimeout+ribbon.ReadTimeout) * (1+ribbon.MaxAutoRetries) * (1+ribbon.MaxAutoRetriesNextServer)

access http://localhost:8080/product/1/review,
some times we will get a fallback result (refer to MyFallbackProvider.java)

fallback

##### Logging:
all request time > 1500ms, request 4 = (1+ribbon.MaxAutoRetries) * (1+ribbon.MaxAutoRetriesNextServer) times


```
2018-06-18 11:14:11.545  INFO 15036 --- [ XNIO-2 task-32] c.e.r.controller.ReviewController        : XNIO-2 task-32 start to execute...
2018-06-18 11:14:13.048  INFO 15036 --- [  XNIO-2 task-1] c.e.r.controller.ReviewController        : XNIO-2 task-1 start to execute...
2018-06-18 11:14:14.070  INFO 15036 --- [ XNIO-2 task-32] c.e.r.controller.ReviewController        : XNIO-2 task-32 finished in ms:2524, result:ProductReview{productId=1, productReview='product review'}
2018-06-18 11:14:14.551  INFO 15036 --- [  XNIO-2 task-2] c.e.r.controller.ReviewController        : XNIO-2 task-2 start to execute...
2018-06-18 11:14:15.359  INFO 15036 --- [  XNIO-2 task-1] c.e.r.controller.ReviewController        : XNIO-2 task-1 finished in ms:2311, result:ProductReview{productId=1, productReview='product review'}
2018-06-18 11:14:16.053  INFO 15036 --- [  XNIO-2 task-3] c.e.r.controller.ReviewController        : XNIO-2 task-3 start to execute...
2018-06-18 11:14:16.376  INFO 15036 --- [  XNIO-2 task-2] c.e.r.controller.ReviewController        : XNIO-2 task-2 finished in ms:1825, result:ProductReview{productId=1, productReview='product review'}
2018-06-18 11:14:18.036  INFO 15036 --- [  XNIO-2 task-3] c.e.r.controller.ReviewController        : XNIO-2 task-3 finished in ms:1983, result:ProductReview{productId=1, productReview='product review'}
```