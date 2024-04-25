package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.graphql.GraphQLExecutor;
import io.tyk.aai.hackathon.graphql.GraphQLRequest;

public class GraphQLController {
    private static final GraphQLExecutor countriesAPI = GraphQLExecutor.createCountriesExecutor();
    private static final GraphQLExecutor annualWorkingHoursAPI = GraphQLExecutor.createAnnualWorkingHoursExecutor();

    public void GraphQLEndpoint(Context ctx) {
        var gqlRequest = ctx.bodyAsClass(GraphQLRequest.class);
        var result = annualWorkingHoursAPI.execute(gqlRequest.query());
        ctx.json(result);
    }
}
