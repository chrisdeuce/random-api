package com.training.post.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class registerToken {
    private String baseHost = "https://reqres.in/api";

    @Test
    private void postRegisterUserNS(){
        RestAssured.baseURI = baseHost;
        String api = "api/register";
        Response response = given().header("Content-Type","application/json").
                body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().log().all().post(api);
    }

    @Test
    private void postRegisterGetDetails(){
        RestAssured.baseURI = baseHost;
        String api = "api/users";
        Response response = given().header("Content-Type","application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").when().log().all().post(api);
    }
}
