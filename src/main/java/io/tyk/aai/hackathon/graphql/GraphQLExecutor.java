package io.tyk.aai.hackathon.graphql;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.util.Map;

public class GraphQLExecutor {
    private final GraphQL graphql;

    public static GraphQLExecutor createCountriesExecutor() {
        return new GraphQLExecutor("graphql/countries-schema.graphqls", () -> RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("countries", GraphQLCountriesFetcher.queryAllCountries)
                )
                .build()
        );
    }

    public static GraphQLExecutor createAnnualWorkingHoursExecutor() {
        return new GraphQLExecutor("graphql/annual-working-hours-schema.graphqls", () -> RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allAnnualWorkingHours", GraphQLAnnualWorkingHoursFetcher.allAnnualWorkingHours)
                        .dataFetcher("annualWorkingHoursByYear", GraphQLAnnualWorkingHoursFetcher.annualWorkingHoursByYear)
                        .dataFetcher("annualWorkingHoursByCountry", GraphQLAnnualWorkingHoursFetcher.annualWorkingHoursByCountry)
                )
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("addAnnualWorkingHours", GraphQLAnnualWorkingHoursFetcher.addAnnualWorkingHours)
                        .dataFetcher("removeAnnualWorkingHours", GraphQLAnnualWorkingHoursFetcher.removeAnnualWorkingHours)
                )
                .build()
        );
    }

    public GraphQLExecutor(String schemaResourcePath, GraphQLRuntimeWiringBuilder wiringBuilder) {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(this.getClass().getClassLoader().getResourceAsStream(schemaResourcePath));

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimeWiring = wiringBuilder.buildRuntimeWiring();
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphql = GraphQL.newGraphQL(schema).build();
    }

    public Map<String, Object> execute(String operationName, String operation) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput(operation)
                .operationName(operationName)
                .build();

        return graphql.execute(executionInput).toSpecification();
    }
}
