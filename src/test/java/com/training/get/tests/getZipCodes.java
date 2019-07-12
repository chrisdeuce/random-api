package com.training.get.tests;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class getZipCodes {
    private String baseHost = "http://api.zippopotam.us";

    @DataProvider
    public static Object[][] zipCodesPlaces(){
    return new Object[][]{
            {"us", "90210", "Beverly Hills"},
            {"mx","11200","Lomas de Sotelo"},
            {"ca", "B2R", "Waverley"}};
    }

    @Test
    private void getAnyzipCode(){
        RestAssured.baseURI = baseHost;
        RequestSpecification request = given();
        Response response = request.request(Method.GET,"/mx/11200");
        //System.out.println(response.asString());
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(response.asString())));
    }

    @Test
    public void requestAnyCode(){
        given().when().get(baseHost+"/mx/11200").
                then().assertThat().
                body("places[1].'place name'",equalTo("Loma Hermosa"));
        //body("places.'place name'",hasItem("Loma Hermosa"));  hasSize(2)
    }

    @Test
    public void validateContentType(){
        given().when().get(baseHost + "/mx/11200").
                then().assertThat().contentType(ContentType.JSON);
    }

    @Test(dataProvider= "zipCodesPlaces")
    public void getAnyCodeWithPar(String countryCode,String zipCode,String expectedPlaceName){
        given().pathParam("countryCode",countryCode).pathParam("zipCode",zipCode).
                when().get(baseHost + "/{countryCode}/{zipCode}").
                then().
                assertThat().body("places[0].'place name'",equalTo(expectedPlaceName));
    }
}
