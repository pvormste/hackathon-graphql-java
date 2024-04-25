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
        int hours
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
                    Integer.parseInt(nextLine[3])
            ));
        }

    }

    public static List<AnnualWorkingHours> all() {
        return data;
    }
}
