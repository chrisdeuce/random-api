package com.training.get.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.synth.SynthTextAreaUI;

import static io.restassured.RestAssured.given;

public class nbaPlayerMovement {
    private String baseHost = "http://stats.nba.com/js/data";

    @Test
    private void getRequest(){
        RestAssured.baseURI = baseHost;
        String body = "";
        Response res = given().header("Content-Type","application/json").body("")
                .when().log().all().get("/playermovement/NBA_Player_Movement.json");
        int returnCode = res.statusCode();
        Assert.assertEquals(returnCode,200);
    }

    @Test
    private void getPlayerMov(){
        RestAssured.baseURI = baseHost;
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET,"/playermovement/NBA_Player_Movement.json");
        System.out.println(response.asString());
    }

    @Test
    private void getPlayerMovHeader(){
        RestAssured.baseURI = baseHost;
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET,"/playermovement/NBA_Player_Movement.json");
        String contentType = response.getHeader("Content-Type");
        String etag = response.getHeader("ETag");
        String datemod = response.getHeader("Last-Modified");
        System.out.println("\n The Content type is: " + contentType);
        System.out.println("\n The ETag is: " + etag);
        System.out.println("\n The latest date of modification is: " + datemod);
        System.out.println("\n");
    }

}
