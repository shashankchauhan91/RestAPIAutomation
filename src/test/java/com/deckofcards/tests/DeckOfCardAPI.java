package com.deckofcards.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckOfCardAPI {
    RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter();
    ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter();
    String baseURL = "https://deckofcardsapi.com/api/deck";
    static String strDeckId="";


    @Test
    public  void getShuffledCards()
    {
        RestAssured.baseURI = baseURL;
        Response response = given().filters(requestLoggingFilter,responseLoggingFilter)
                .queryParam("deck_count",1)
                .get("/new/shuffle/");
        Assertions.assertEquals(200,response.getStatusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        strDeckId = jsonPathEvaluator.get("deck_id");
    }
    @Test
    public  void getDrawCard()
    {
        RestAssured.baseURI = baseURL;
        Response response = given().filters(requestLoggingFilter,responseLoggingFilter)
                .queryParam("deck_count",1)
                .pathParam("variable",strDeckId)
                .get("{variable}/draw");
        Assertions.assertEquals(200,response.getStatusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        boolean bSuccess = jsonPathEvaluator.get("success");
        Assertions.assertTrue(bSuccess);

    }



//    public String getDeckID()
//    {
//        RestAssured.baseURI = baseURL;
//        Response response = given().filters(requestLoggingFilter,responseLoggingFilter)
//                .queryParam("deck_count",1)
//                .get("/new/shuffle/");
//        JsonPath jsonPathEvaluator = response.jsonPath();
//        return jsonPathEvaluator.get("deck_id");
//
//    }


}
