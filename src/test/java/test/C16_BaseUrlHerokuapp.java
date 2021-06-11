package test;

import BaseUrlDepo.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends BaseUrlHerokuapp {

    @Test
    public void test01(){

        /*
            1-  https://restful-booker.herokuapp.com/booking endpointine
            bir GET request gonderdigimizde donen response’un
            status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin
         */


        // 1- request url ve body'sini olustur
        specHerokuapp.pathParam("pp1","booking");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                        spec(specHerokuapp).
                        when().
                        get("/{pp1}");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid", Matchers.hasSize(10));

    }

    @Test
    public void test02(){
         /*
        2- https://restful-booker.herokuapp.com/booking endpointine
        asagidaki body’ye sahip bir POST request gonderdigimizde
        donen response’un status code’unun 200 oldugunu ve “firstname” degerinin “Ahmet” oldugunu test edin

            {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                   "checkin" : "2021-06-01",
                   "checkout" : "2021-06-10"
                      },
            "additionalneeds" : "wi-fi" }
         */


        // 1- Request url ve body olustur

        specHerokuapp.pathParam("pp1","booking");


        JSONObject requestBody =new JSONObject();
        JSONObject innerBody= new JSONObject();

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);

        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");
        requestBody.put("bookingdates",innerBody);

        requestBody.put("additionalneeds","wi-fi");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                                spec(specHerokuapp).
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                post("/{pp1}");

        response.prettyPrint();

        // 4- Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                body("booking.firstname",Matchers.equalTo("Ahmet"));

    }

}
