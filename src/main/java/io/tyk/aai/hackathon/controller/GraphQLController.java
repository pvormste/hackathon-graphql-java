package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.graphql.GraphQLExecutor;
import io.tyk.aai.hackathon.graphql.GraphQLRequest;

import java.util.HashMap;
import java.util.Map;

public class GraphQLController {
    private static final GraphQLExecutor countriesAPI = GraphQLExecutor.createCountriesExecutor();
    private static final GraphQLExecutor annualWorkingHoursAPI = GraphQLExecutor.createAnnualWorkingHoursExecutor();

    public void GraphQLEndpoint(Context ctx) {
        var gqlRequest = ctx.bodyAsClass(GraphQLRequest.class);
        var variables = gqlRequest.variables();
        if (variables == null) {
            variables = new HashMap<>();
        }
        var result = annualWorkingHoursAPI.execute(gqlRequest.operationName(), gqlRequest.query(), variables);
        ctx.json(result);
    }
}
