package io.tyk.aai.hackathon.graphql;

import graphql.schema.idl.RuntimeWiring;

@FunctionalInterface
public interface GraphQLRuntimeWiringBuilder {
    RuntimeWiring buildRuntimeWiring();
}
