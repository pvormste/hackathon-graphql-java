FROM eclipse-temurin:22-jdk-alpine
WORKDIR /opt/app
EXPOSE 9555
COPY . /opt/app
RUN ./gradlew build
CMD TERM=dumb ./gradlew run