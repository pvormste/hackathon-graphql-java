package io.tyk.aai.hackathon.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

public record StoredRequest(
        String id,
        String tag,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        Date date,
        Map<String, String> headers,
        String body
) {
    private static final List<StoredRequest> storedRequests = new ArrayList<>();

    public static void store(String id, String tag, Map<String, String> headers, String body) {
        var date = new Date();
        storedRequests.add(new StoredRequest(
                id,
                tag,
                date,
                headers,
                body
        ));
    }

    public static List<StoredRequest> all() {
        return storedRequests;
    }

    public static List<StoredRequest> byTag(String tag) {
        return storedRequests.stream()
                .filter(storedRequest -> storedRequest.tag.equals(tag))
                .toList();
    }

    public static void clear() {
        storedRequests.clear();
    }
}
