package io.tyk.aai.hackathon.graphql;

import graphql.schema.DataFetcher;
import io.tyk.aai.hackathon.data.Country;

import java.util.List;

public class GraphQLCountriesFetcher {
    public static DataFetcher<List<Country>> queryAllCountries = environment -> Country.allCountries();
}
