# Spring Boot RESTFul API Example
Example for Spring Boot + RESTful Service, using Maven build tool.

### 1. Technologies
* Spring Boot 1.5.10.RELEASE

### 2. To Run this project locally
```shell
$ git clone https://github.com/MasterRyo/mymoney-api.git
$ ./mvnw clean package
$ echo #basic-security
$ java -jar target/mymoney-api-1.0.0-SNAPSHOT.jar
$ echo #oauth-security
$ java -Dspring.profiles.active=oauth-security -jar target/mymoney-api-1.0.0-SNAPSHOT.jar
```

##### Simple request for Postman (basic-security): 
```
GET /categorias HTTP/1.1
Host: localhost:8080
Authorization: Basic YWRtaW5AbXltb25leS5jb206YWRtaW4=
```

##### Simple request for Postman (oauth-security):
```
#### request access token
POST /oauth/token HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
Authorization: Basic YW5ndWxhcjpAbmd1bEByMA==
username=admin%40mymoney.com&password=admin&grant_type=password

#### request data
GET /categorias HTTP/1.1
Host: localhost:8080
Authorization: Bearer <access_token>
```

### 3. Open this project in Spring Tool Suite(STS) IDE
1. File/Open Project from File System...
2. Done. 
