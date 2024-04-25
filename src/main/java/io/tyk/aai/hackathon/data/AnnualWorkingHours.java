package io.tyk.aai.hackathon.data;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public record AnnualWorkingHours(
        String countryName,
        String countryAbbrev,
        int year,
        float hours
) {
    private static List<AnnualWorkingHours> data = new ArrayList<>();

    public static void readData() throws IOException, CsvValidationException {
        InputStream csvInputStream = null;
        try {
            csvInputStream = AnnualWorkingHours.class.getClassLoader().getResourceAsStream("data/annual-working-hours-per-worker.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (csvInputStream == null) {
            return;
        }

        var reader = new InputStreamReader(csvInputStream);
        var csvReader = new CSVReaderHeaderAwareBuilder(reader)
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .build()
                )
                .build();


        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            data.add(new AnnualWorkingHours(
                    nextLine[0],
                    nextLine[1],
                    Integer.parseInt(nextLine[2]),
                    Float.parseFloat(nextLine[3])
            ));
        }

    }

    public static List<AnnualWorkingHours> all() {
        return data;
    }

    public static List<AnnualWorkingHours> byYear(int year) {
        List<AnnualWorkingHours> result = new ArrayList<>();
        for (AnnualWorkingHours annualWorkingHours : data) {
            if (annualWorkingHours.year == year) {
                result.add(annualWorkingHours);
            }
        }
        return result;
    }

    public static List<AnnualWorkingHours> byCountry(String countryAbbrev) {
        List<AnnualWorkingHours> result = new ArrayList<>();
        for (AnnualWorkingHours annualWorkingHours : data) {
            if (annualWorkingHours.countryAbbrev.equals(countryAbbrev)) {
                result.add(annualWorkingHours);
            }
        }
        return result;
    }

    public static AnnualWorkingHours add(String countryName, String countryAbbrev, int year, float hours) {
        var annualWorkingHours = new AnnualWorkingHours(countryName, countryAbbrev, year, hours);
        data.add(annualWorkingHours);
        return annualWorkingHours;
    }

    public static boolean remove(String countryAbbrev, int year) {
        var foundElements = data.stream()
                .filter(annualWorkingHours ->
                    annualWorkingHours.countryAbbrev.equals(countryAbbrev) && annualWorkingHours.year == year
                ).toList();

        if (foundElements.isEmpty()) {
            return false;
        }

        foundElements.forEach(annualWorkingHours ->
                    data.remove(annualWorkingHours)
                );

        return true;
    }
}
