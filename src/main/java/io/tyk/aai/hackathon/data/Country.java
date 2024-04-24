package io.tyk.aai.hackathon.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Country(String code, String name) {
    private static final List<Country> countries = Arrays.asList(
            new Country("de", "Germany"),
            new Country("pl", "Poland"),
            new Country("uk", "United Kingdom")
    );

    public static List<Country> allCountries() {
        return countries;
    }
}
