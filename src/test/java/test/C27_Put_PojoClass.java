package test;

import BaseUrlDepo.BaseUrlJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlace;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_PojoClass extends BaseUrlJsonPlaceHolder {
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

        Response body : // expected data

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

        // request body'i pojo class ile olusturalim

        PojoJsonPlace requestBodyPojo = new PojoJsonPlace("Ahmet","Merhaba",10,70);
        System.out.println(requestBodyPojo); // PojoJsonPlace{title='Ahmet', body='Merhaba', userId=10, id=70}

        // 2- soruda varsa expected data olustur
        PojoJsonPlace expectedDataPojo = new PojoJsonPlace("Ahmet","Merhaba",10,70);
        System.out.println("expected data pojo : " + expectedDataPojo);

        // 3 - Response olustur, request gonderip sonucu response'a ata

        Response response=given().
                            contentType(ContentType.JSON).
                            spec(specJsonPlace).
                            when().body(requestBodyPojo).
                            put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assert
        // expectedDataPojo ==> response
        // response'i da pojo'ya cevirebiliriz
        PojoJsonPlace responsePojo = response.as(PojoJsonPlace.class);
        System.out.println("response pojo : " + responsePojo);

        assertEquals(expectedDataPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expectedDataPojo.getBody(),responsePojo.getBody());
        assertEquals(expectedDataPojo.getUserId(),responsePojo.getUserId());
        assertEquals(expectedDataPojo.getId(),responsePojo.getId());
    }
}
