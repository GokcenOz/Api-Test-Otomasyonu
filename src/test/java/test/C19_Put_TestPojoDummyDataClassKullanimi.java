package test;

import BaseUrlDepo.BaseUrlJsonPlaceHolder;
import TestDataDeposu.TestDataJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_Put_TestPojoDummyDataClassKullanimi extends BaseUrlJsonPlaceHolder {
    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body’e sahip bir PUT request yolladigimizda donen response’in
        status kodunun 200,
        content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

         Request Body

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }

        Response body (Expected Data) :

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
     */

    @Test
    public void test01(){

        // 1- request url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        JSONObject requestBody= testDataJsonPlaceHolder.putRequestBodyOlustur();

        // 2- Soruda varsa expected data olustur
        JSONObject expectedDataJson= testDataJsonPlaceHolder.putRequestExpectedBodyOlustur();

        // Response olustur, request gonderip response'a kaydet

        Response response=given().
                            spec(specJsonPlace).
                            contentType(ContentType.JSON).
                            when().
                            body(requestBody.toString()).
                            put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- assertions

        JsonPath responseJPath=response.jsonPath();

        //  status kodunun 200,
        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        //        content type’inin “application/json; charset=utf-8”,
        assertEquals(testDataJsonPlaceHolder.contentType, response.getContentType());
        //        Connection header degerinin “keep-alive”
        assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response.getHeader("Connection"));
        // body ile ilgili testleri JsonPath kullanarak yapalim
        assertEquals(expectedDataJson.getString("title"),responseJPath.getString("title"));
        assertEquals(expectedDataJson.getString("body"),responseJPath.getString("body"));
        assertEquals(expectedDataJson.getInt("userId"),responseJPath.getInt("userId"));
        assertEquals(expectedDataJson.getInt("id"),responseJPath.getInt("id"));
    }

}
