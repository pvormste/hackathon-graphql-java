plugins {
    id("java")
    id("application")
}

group = "io.tyk.aai.hackathon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "io.tyk.aai.hackathon.Main"
}

dependencies {
    // Javalin and deps
    implementation("io.javalin:javalin:6.1.3")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("gg.jte:jte:3.1.10")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    // Logging
    implementation("org.slf4j:slf4j-simple:2.0.10")

    // GraphQL
    implementation("com.graphql-java:graphql-java:22.0")

    // CSV
    implementation("com.opencsv:opencsv:5.9")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}