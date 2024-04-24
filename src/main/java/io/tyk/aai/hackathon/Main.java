package io.tyk.aai.hackathon;

import io.javalin.Javalin;
import io.tyk.aai.hackathon.controller.DebugController;
import io.tyk.aai.hackathon.controller.GraphQLController;
import io.tyk.aai.hackathon.controller.RestController;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;
import io.tyk.aai.hackathon.middleware.RequestStoreMiddleware;

public class Main {
    public static void main(String[] args) {
        try {
            AnnualWorkingHours.readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        var graphqlController = new GraphQLController();

        var app = Javalin.create(config -> {

        })
                .before(RequestStoreMiddleware::storeRequest)
                .get("/", ctx -> ctx.result("Hello World"))
                .post("/graphql", graphqlController::GraphQLEndpoint)
                .get("/rest/annual-working-hours", RestController::getAllAnnualWorkingHours)
                .get("/debug/requests", DebugController::getStoredRequests)
                .delete("/debug/requests", DebugController::clearAllRequests)
                .start(9555);
    }
}