package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.graphql.GraphQLCountries;
import io.tyk.aai.hackathon.graphql.GraphQLRequest;

public class GraphQLController {
    private GraphQLCountries countriesAPI = new GraphQLCountries();

    public void GraphQLEndpoint(Context ctx) {
        var gqlRequest = ctx.bodyAsClass(GraphQLRequest.class);
        var result = countriesAPI.execute(gqlRequest.query());
        ctx.json(result);
    }
}
