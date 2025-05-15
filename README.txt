
# Microservice Demo 

// cd C:\HONG\DEVOPSTest\zTESTh\microservices-demo

## clean

mvn clean

## build

mvn clean package

// cd C:\HONG\DEVOPSTest\zTESTh\microservices-demo\ProductServer

## run PostGres DB

docker compose up

## run product service

java -jar .\target\Product-Server-Microservice-0.0.1-SNAPSHOT.jar --server.port=8081 --DB_SERVER=127.0.0.1:5432 --POSTGRES_DB=test-db --POSTGRES_USER=postgres --POSTGRES_PASSWORD=postgres 

// cd C:\HONG\DEVOPSTest\zTESTh\microservices-demo\ProductWeb

## run web service
java -jar .\target\Product-Web-Microservice-0.0.1-SNAPSHOT.jar --server.port=8080 --acpm.PRODUCT_SERVICE_URL=http://localhost:8081/products

## test browser

http://localhost:8080/product.html