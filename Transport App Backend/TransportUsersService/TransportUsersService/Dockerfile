FROM openjdk:11.0.10-jre
WORKDIR usr/src
ENV MYSQLDB_DATABASE transportDB
ENV MYSQLDB_URl mysql://localhost:3306/transportDB
ADD ./target/TransportUsersService-0.0.1-SNAPSHOT.jar /usr/src/TransportUsersService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","TransportUsersService-0.0.1-SNAPSHOT.jar"]
