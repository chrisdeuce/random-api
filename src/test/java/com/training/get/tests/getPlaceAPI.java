package com.training.get.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.EasyMock2Matchers.equalTo;

public class getPlaceAPI {
    private String baseHost = "https://maps.googleapis.com";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void getLocation(){
        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
    }

}
