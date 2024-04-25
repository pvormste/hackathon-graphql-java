package io.tyk.aai.hackathon.graphql;

import graphql.schema.DataFetcher;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;

import java.util.List;
import java.util.Map;

public class GraphQLAnnualWorkingHoursFetcher {
    public static DataFetcher<List<AnnualWorkingHours>> allAnnualWorkingHours = environment -> AnnualWorkingHours.all();
    public static DataFetcher<List<AnnualWorkingHours>> annualWorkingHoursByYear = environment -> AnnualWorkingHours.byYear(environment.getArgument("year"));
    public static DataFetcher<List<AnnualWorkingHours>> annualWorkingHoursByCountry = environment -> AnnualWorkingHours.byCountry(environment.getArgument("countryAbbrev"));
    public static DataFetcher<AnnualWorkingHours> addAnnualWorkingHours = environment -> {
      Map<String, Object> input = environment.getArgument("annualWorkingHours");
      String countryName = (String) input.getOrDefault("countryName", "");
      String countryAbbrev = (String) input.getOrDefault("countryAbbrev", "");
      int year = (int) input.getOrDefault("year", 0);
      double hours = (double) input.getOrDefault("hours", 0);
      return AnnualWorkingHours.add(countryName, countryAbbrev, year, (float) hours);
    };
    public static DataFetcher<Boolean> removeAnnualWorkingHours = environment -> {
      String countryAbbrev = environment.getArgument("countryAbbrev");
      int year = environment.getArgument("year");
      return AnnualWorkingHours.remove(countryAbbrev, year);
    };
}
