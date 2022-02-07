FROM openjdk:11

# Configure MYSQL instance
RUN apt-get update

COPY ./target/auth-module-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch auth-module-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","auth-module-0.0.1-SNAPSHOT.jar"]
