plugins {
    id("java")
}

group = "io.tyk.aai.hackathon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Javalin and deps
    implementation("io.javalin:javalin:6.1.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    // Logging
    implementation("org.slf4j:slf4j-simple:2.0.10")

    // GraphQL
    implementation("com.graphql-java:graphql-java:22.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}