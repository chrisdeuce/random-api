package com.training.get.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getInfoUsingToken {
    private String baseHost = "http://api.football-data.org/v2";
    private String accessToken = "1ef6faab65b64677bf46fbf441989878";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void getStandings(){
        String api = "competitions/BL1/standings";
        String textrsp = "";
        Response response = given().header("X-Auth-Token",accessToken).when().log().all().
                get(api);//.then().statusCode(200).extract().response();

        textrsp = response.toString();
        System.out.println(textrsp);
    }
}
