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

public class C22_Put_Deserialization extends BaseUrlJsonPlaceHolder {

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

        Response body :

            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
     */

    @Test
    public void test01(){
        // 1- Request url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        HashMap<String,Object> requestBodyMap = testDataJsonPlaceHolder.requestBodyMapOlustur();

        // 2- Soruda isteniyorsa expected data olustur
        HashMap<String,Object> expectedDataMap = testDataJsonPlaceHolder.requestBodyMapOlustur();

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response= given().
                                contentType(ContentType.JSON).
                                spec(specJsonPlace).
                                when().
                                body(requestBodyMap).
                                put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assertion
        // expectedDatMap  ===== response objesi
        HashMap<String,Object> responseMap = response.as(HashMap.class);
        // response'i as() metodu kullanarak Map'e cevirelim
        // expectedDataMap == responseMap
        System.out.println(responseMap);
        System.out.println(expectedDataMap);

        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        assertEquals(expectedDataMap.get("id"),responseMap.get("id"));
        assertEquals(expectedDataMap.get("title"),responseMap.get("title"));
        assertEquals(expectedDataMap.get("body"),responseMap.get("body"));
        assertEquals(expectedDataMap.get("userId"),responseMap.get("userId"));

    }
}
