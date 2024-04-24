package io.tyk.aai.hackathon;

import io.javalin.Javalin;
import io.tyk.aai.hackathon.controller.GraphQLController;

public class Main {
    public static void main(String[] args) {
        var graphqlController = new GraphQLController();

        var app = Javalin.create(config -> {

        })
                .get("/", ctx -> ctx.result("Hello World"))
                .post("/graphql", graphqlController::GraphQLEndpoint)
                .start(9555);
    }
}