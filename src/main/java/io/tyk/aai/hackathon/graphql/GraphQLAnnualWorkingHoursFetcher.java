package io.tyk.aai.hackathon.graphql;

import graphql.schema.DataFetcher;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;

import java.util.List;

public class GraphQLAnnualWorkingHoursFetcher {
    public static DataFetcher<List<AnnualWorkingHours>> allAnnualWorkingHours = environment -> AnnualWorkingHours.all();
    public static DataFetcher<List<AnnualWorkingHours>> annualWorkingHoursByYear = environment -> AnnualWorkingHours.byYear(environment.getArgument("year"));
    public static DataFetcher<List<AnnualWorkingHours>> annualWorkingHoursByCountry = environment -> AnnualWorkingHours.byCountry(environment.getArgument("countryAbbrev"));
}
