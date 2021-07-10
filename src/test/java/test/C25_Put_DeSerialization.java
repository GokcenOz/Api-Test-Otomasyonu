package test;

import BaseUrlDepo.BaseUrlJsonPlaceHolder;
import TestDataDeposu.TestDataJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_Put_DeSerialization extends BaseUrlJsonPlaceHolder {
     /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body’e sahip bir PUT request yolladigimizda donen response’in
        status kodunun 200,
        content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

         Request Body

            {
            "title":"Hasan",
            "body":"Hoscakal",
            "userId":10.0,
            "id":70.0
            }

        Response body : // expected data

            {
            "title":"Hasan",
            "body":"Hoscakal",
            "userId":10.0,
            "id":70.0
            }
     */

    @Test
    public void test01(){
        // 1- request body ve url olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);
        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        HashMap<String,Object> requestBody=testDataJsonPlaceHolder.requestBodyMapOlustur("Hasan",
                "Hoscakal",10.0,70.0);
        // 2- Soruda varsa expected data olustur

        HashMap<String,Object> expectedDataMap = testDataJsonPlaceHolder.requestBodyMapOlustur("Hasan",
                "Hoscakal",10.0,70.0);

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                                spec(specJsonPlace).
                                contentType(ContentType.JSON).
                                when().
                                body(requestBody).
                                put("/{pp1}/{pp2}");

        HashMap<String ,Object> responseMap =response.as(HashMap.class);

        // 4- Assertion


        //        status kodunun 200,
        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        //        content type’inin “application/json; charset=utf-8”,
        assertEquals(testDataJsonPlaceHolder.contentType,response.getContentType());
        //        Connection header degerinin “keep-alive”
        assertEquals(testDataJsonPlaceHolder.connectionHeaderDegeri,response.getHeader("Connection"));

        // expected data ===== response
        // expectedDataMap ==== responseMap

        System.out.println(expectedDataMap);
        // {id=70.0, title=Hasan, body=Hoscakal, userId=10.0}

        System.out.println(responseMap);
        //{id=70.0, title=Hasan, body=Hoscakal, userId=10.0}

        assertEquals(expectedDataMap.get("id"),responseMap.get("id"));
        assertEquals(expectedDataMap.get("title"),responseMap.get("title"));
        assertEquals(expectedDataMap.get("body"),responseMap.get("body"));
        assertEquals(expectedDataMap.get("userId"),responseMap.get("userId"));
    }
}
