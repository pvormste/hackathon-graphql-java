package io.tyk.aai.hackathon.graphql;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.Map;

public class GraphQLCountries {
    private final GraphQL graphql;

    public GraphQLCountries() {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(this.getClass().getClassLoader().getResourceAsStream("graphql/schema.graphqls"));

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphql = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("countries", GraphQLCountriesFetcher.queryAllCountries)
                )
                .build();
    }

    public Map<String, Object> execute(String operation) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput(operation)
                .build();

        return graphql.execute(executionInput).toSpecification();
    }
}
