package io.tyk.aai.hackathon.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.tyk.aai.hackathon.data.AnnualWorkingHours;

public class RestController {
    public static void getAllAnnualWorkingHours(Context ctx) {
        var filterByParam = ctx.queryParam("filterBy") != null ? ctx.queryParam("filterBy") : "";
        var filterValueParam = ctx.queryParam("filterValue") != null ? ctx.queryParam("filterValue") : "";
        if (!filterByParam.isEmpty()) {
           if (filterValueParam.isEmpty()) {
               ctx.status(HttpStatus.BAD_REQUEST);
               return;
           }

           switch (filterByParam) {
               case "name":
                   ctx.json(AnnualWorkingHours.byCountryName(filterValueParam));
                   return;
               case "abbrev":
                   ctx.json(AnnualWorkingHours.byCountryAbbrev(filterValueParam));
                   return;
               case "year":
                   int year;
                   try {
                       year = Integer.parseInt(filterValueParam);
                   } catch (NumberFormatException e) {
                       ctx.status(HttpStatus.BAD_REQUEST);
                       return;
                   }

                   ctx.json(AnnualWorkingHours.byYear(year));
                   break;
               default:
                   ctx.status(HttpStatus.BAD_REQUEST);
                   return;
           }
        }
        ctx.json(AnnualWorkingHours.all());
    }
}
