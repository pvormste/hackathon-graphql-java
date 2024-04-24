package io.tyk.aai.hackathon;

import io.javalin.Javalin;
import io.tyk.aai.hackathon.controller.DebugController;
import io.tyk.aai.hackathon.controller.GraphQLController;
import io.tyk.aai.hackathon.controller.RestController;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;
import io.tyk.aai.hackathon.middleware.RequestStoreMiddleware;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        try {
            AnnualWorkingHours.readData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        var graphqlController = new GraphQLController();

        var app = Javalin.create(config -> {
            config.router.apiBuilder(() -> {
                get("/", ctx -> ctx.result("Hello World"));
                path("/graphql", () -> {
                    before(RequestStoreMiddleware::storeRequest);
                    post(graphqlController::GraphQLEndpoint);
                });

                get("/rest/annual-working-hours", RestController::getAllAnnualWorkingHours);

                path("/debug", () -> {
                    path("/requests", () -> {
                        get(DebugController::getStoredRequests);
                        delete(DebugController::clearAllRequests);
                    });
                });

            });
        }).start(9555);
    }
}