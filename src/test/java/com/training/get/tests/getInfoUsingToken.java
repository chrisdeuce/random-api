package com.training.get.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void logStandings(){
        given().header("X-Auth-Token",accessToken).log().all().when().
                get("competitions/BL1/standings").then().
                log().all().assertThat().contentType(ContentType.JSON);
    }


    @Test
    public void getMatches(){
        String api="competitions/2003/matches/?matchday=20";
        given().header("X-Auth-Token",accessToken).log().all().when().
                get(api).then().
                log().all().assertThat().contentType(ContentType.JSON);
    }

    //https://api.football-data.org/v2/competitions

    @Test
    public void getCompetitions(){
        String api="competitions";
        given().header("X-Auth-Token",accessToken).log().all().when().
                get(api).then().
                log().all().assertThat().contentType(ContentType.JSON);
    }
}
