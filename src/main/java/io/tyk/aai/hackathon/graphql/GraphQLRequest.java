package io.tyk.aai.hackathon.graphql;

import java.util.Map;

public record GraphQLRequest(
        String operationName,
        String query,
        Map<String, Object> variables
) {
}
