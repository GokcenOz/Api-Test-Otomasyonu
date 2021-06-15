package test;

import BaseUrlDepo.BaseUrlHerokuapp1;
import TestDataDeposu.TestDataHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends BaseUrlHerokuapp1 {
     /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id haric asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }

    	            	Response Body
    	            {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                        ,
                        "additionalneeds":"wi-fi"
                    }
     */

    @Test
    public void test01(){
        // 1- request url ve body olustur
        specHerokuapp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp=new TestDataHerokuapp();
        JSONObject requestBody=testDataHerokuapp.getRuestBodyOlustur();

        // 2- soruda varsa expected data olustur

        JSONObject expectedData=testDataHerokuapp.postRequestExpectedDataOlustur();

        // 3- response olustur, request gonderip response'i kaydet

        Response response= given().
                                spec(specHerokuapp).
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody.toString()).
                                post("/{pp1}");

        response.prettyPrint();

        // 4- Assertions
        JsonPath responseJPath=response.jsonPath();
        assertEquals(expectedData.getJSONObject("booking").getString("firstname"),responseJPath.getString("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),responseJPath.getString("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),responseJPath.getInt("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),responseJPath.getBoolean("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),responseJPath.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),responseJPath.getString("booking.bookingdates.checkout"));
        assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),responseJPath.getString("booking.additionalneeds"));
    }
}
