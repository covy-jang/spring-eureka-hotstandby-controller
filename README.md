# Active-HotStandby Controller

분산 처리 시스템이 아닌 Active-Standby 구성이 필요할 때 사용할 수 있는 기능입니다.
Eureka Server Application 등록되어 있는 대상 중 가장 먼저 등록된 대상을 Active 선정합니다.

## Usage in Spring Boot

``` Applicatipn.java
@EnableEurekaClient
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

``` application-local.yml
eureka:
  dashboard:
    path: /eureka/dashboard
  instance:
    hostname: 127.0.0.1
  client:
    register-with-eureka: true
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## Checking out and Building
To check out the project and build from source, do the following:

``` Build
git clone https://github.com/paper-airplane-94/redu-controller.git

cd redu-controller

./gradlew build
```

``` Run
git clone https://github.com/paper-airplane-94/redu-controller.git

cd redu-controller

./gradlew bootRun
```
