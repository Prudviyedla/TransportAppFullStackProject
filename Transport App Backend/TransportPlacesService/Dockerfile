FROM openjdk:11.0.10-jre
WORKDIR usr/src
ENV MONGODB_DATABASE TransportDB
ENV MONGODB_URL mongodb://localhost:27017/TransportDB
ADD ./target/places-0.0.1-SNAPSHOT.jar /usr/src/places-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","places-0.0.1-SNAPSHOT.jar"]