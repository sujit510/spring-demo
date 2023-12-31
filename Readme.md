## Simple Java spring + MySQL Applicaiton

### Features
1. Vehicle CRUD using MySQL DB
2. Employee CRUD using Postgres DB (Coming Soon)

### How to start the app
#### Non-docker
* Build
```./gradlew build```
* Run
```./gradlew bootRun  -x test```

#### Docker
* Prepare image:
<br>```docker build -t spring-demo .```
* Run With compose: 
<br/>```docker-compose up -d```
* Run Without compose
    ##### DB
    ```docker run --name mysql -it -e MYSQL_ROOT_PASSWORD=admin --network=spring-demo-network -d -p 3306:3306 mysql```
    ##### App
    ```docker build -t spring-demo .```
    <br/>```docker run --name spring-demo --network=spring-demo-network -p 8080:8080 spring-demo```
