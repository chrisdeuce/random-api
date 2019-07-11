package com.training.get.tests;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class getBoxScore {
    private String baseHost = "http://data.nba.net/prod/v1";

    @Test
    private void getPlayerBoxScore(){
        RestAssured.baseURI = baseHost;
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET,"/20170201/0021600732_boxscore.json");
        System.out.println(response.asString());
        //pretty
        //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(response.asString())));
    }

    @Test
    private void getPlayerBoxValidateNodeValues(){
        RestAssured.baseURI = baseHost;
        String api = "/20170201/0021600732_boxscore.json";
        ValidatableResponse res = given().header("Content-Type","application/json").when().
               get(api).then().assertThat().body("basicGameData.seasonStageId",is(2));

        ValidatableResponse seasonY = given().header("Content-Type","application/json").when().
                get(api).then().assertThat().body("basicGameData.seasonYear",is("2016"));
    }

    @Test
    private void getPlayerBoxValidateComplexNodeValues(){
        RestAssured.baseURI = baseHost;
        String api = "/20170201/0021600732_boxscore.json";
        ValidatableResponse res = given().header("Content-Type","application/json").when().
                get(api).then().assertThat().body("basicGameData.arena.name",is("TD Garden"));

    }
}
