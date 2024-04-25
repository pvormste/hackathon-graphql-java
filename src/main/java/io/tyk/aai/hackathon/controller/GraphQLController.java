package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.graphql.GraphQLExecutor;
import io.tyk.aai.hackathon.graphql.GraphQLRequest;

public class GraphQLController {
    private static GraphQLExecutor countriesAPI = GraphQLExecutor.createCountriesExecutor();

    public void GraphQLEndpoint(Context ctx) {
        var gqlRequest = ctx.bodyAsClass(GraphQLRequest.class);
        var result = countriesAPI.execute(gqlRequest.query());
        ctx.json(result);
    }
}
