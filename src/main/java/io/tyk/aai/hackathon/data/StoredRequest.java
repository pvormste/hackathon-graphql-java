package io.tyk.aai.hackathon.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public record StoredRequest(
        String id,
        Map<String, String> headers,
        String body,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        Date date
) {
    private static final List<StoredRequest> storedRequests = new ArrayList<>();

    public static void store(String id, Map<String, String> headers, String body) {
        var date = new Date();
        storedRequests.add(new StoredRequest(
                id,
                headers,
                body,
                date
        ));
    }

    public static List<StoredRequest> getAll() {
        return storedRequests;
    }

    public static void clear() {
        storedRequests.clear();
    }
}
