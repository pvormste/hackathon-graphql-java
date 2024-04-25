package io.tyk.aai.hackathon.middleware;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.data.StoredRequest;

import java.util.UUID;

public class RequestStoreMiddleware {
    public static void storeRequest(Context ctx) {
        var id = UUID.randomUUID().toString();
        var headers = ctx.headerMap();
        var body = ctx.body();
        StoredRequest.store(id, headers, body);
    }
}
