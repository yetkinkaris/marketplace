# Marketplace
Marketplace is a simple REST webservice using Spring Boot.

## Build

Use Maven Wrapper to build application. 
./mvnw clean install for Linux
./mvnw.cmd clean install for Windows

## Run
It can be run on any platform with JRE11 with following script.
mvn spring-boot:run

##API Doc

Can be accessed at /swagger-ui.html and /v2/api-docs

##QAs

Q. You do not need to add authentication to your web service, but propose a protocol / method and
justify your choice.*
A. Using OAuth2 protocol is a good option. It is a widely used, supported and accepted standard protocol.

Q. How can you make the service redundant? What considerations should you do?*
A. There should be a mechanism to know that service is really working. Heartbeat is one option. Another option may be a temporary lock such that the one that acquires the lock should update it periodically otherwise another instance acquires the lock. 
 Instances should be stateless thus they can replace each other. States can be hold on shared NFSs or replicated databases.