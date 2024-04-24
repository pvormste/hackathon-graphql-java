package io.tyk.aai.hackathon.middleware;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.data.StoredRequest;

public class RequestStoreMiddleware {
    public static void storeRequest(Context ctx) {
        if (!ctx.path().equals("/graphql")) {
            return;
        }

        var headers = ctx.headerMap();
        var body = ctx.body();
        StoredRequest.store(headers, body);
    }
}
