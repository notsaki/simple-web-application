FROM maven:3.8.3-openjdk-17
WORKDIR ./
COPY . .
RUN rm ./src/main/resources/application.properties
RUN mvn clean install -Dmaven.test.skip=true
CMD mvn spring-boot:run