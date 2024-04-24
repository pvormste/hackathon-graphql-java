package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;

public class RestController {
    public static void getAllAnnualWorkingHours(Context ctx) {
        ctx.json(AnnualWorkingHours.all());
    }
}
