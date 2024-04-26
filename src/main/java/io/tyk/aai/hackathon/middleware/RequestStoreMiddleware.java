package io.tyk.aai.hackathon.middleware;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.data.StoredRequest;

import java.util.UUID;

public class RequestStoreMiddleware {
    private static final String HEADER_X_DEBUG_TAG = "X-Debug-Tag";

    public static void storeRequest(Context ctx) {
        var id = UUID.randomUUID().toString();
        var tag = "";
        var headers = ctx.headerMap();
        var body = ctx.body();

        if (ctx.headerMap().containsKey(HEADER_X_DEBUG_TAG)) {
            tag = ctx.header(HEADER_X_DEBUG_TAG);
        }

        StoredRequest.store(id, tag, headers, body);
    }
}
