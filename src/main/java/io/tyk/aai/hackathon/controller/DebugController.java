package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.tyk.aai.hackathon.data.StoredRequest;

public class DebugController {
    public static void getStoredRequests(Context ctx) {
        var tagParamValue = ctx.queryParam("tag");
        if (tagParamValue != null && !tagParamValue.isEmpty()) {
            ctx.json(StoredRequest.byTag(tagParamValue).reversed());
            return;
        }

        ctx.json(StoredRequest.all().reversed());
    }

    public static void clearAllRequests(Context ctx) {
        StoredRequest.clear();
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
