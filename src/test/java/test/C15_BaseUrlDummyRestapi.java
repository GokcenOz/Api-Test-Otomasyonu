package test;

import BaseUrlDepo.BaseUrlJsonPlaceHolder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class C15_BaseUrlDummyRestapi extends BaseUrlJsonPlaceHolder {


    @Test
    public void test01(){
        //    Soru 1-  https://jsonplaceholder.typicode.com/posts endpointine
        //    bir GET request gonderdigimizde donen response’un status code’unun 200 oldugunu
        //    ve Response’ta 100 kayit oldugunu test edin

        // 1- Request url ve body'sini olustur

        // String url="https://jsonplaceholder.typicode.com/posts";


        // Framework'umuzu dinamik yapmak ve base url'de bir degisiklik oldugunda
        // tek tek tum test class'larini incelemek yerine sadece
        // BaseUrl class'ina gidip iradan tek degisiklikle sorunui cozmek icin
        // b yontem TERCIH EDILIR

        // 1- Request url ve body olustur
        specJsonPlace.pathParam("pp1","posts");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonderip donen sonucu kaydet
        Response response=given().spec(specJsonPlace).when().get("/{pp1}");
        response.prettyPrint();

        // 4- assertions

        response.
                then().
                assertThat().
                statusCode(200).
                body("title", Matchers.hasSize(100));
    }

    @Test
    public void test02(){
        // 2- https://jsonplaceholder.typicode.com/posts/44 endpointine
        //    bir GET request gonderdigimizde donen response’un status code’unun 200 oldugunu
        //    ve “title” degerinin “optio dolor molestias sit” oldugunu test edin


        // Request url ve body olustur

        specJsonPlace.pathParams("pp1","posts","pp2",44);

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonderip donen sonucu kaydet

        Response response=given().
                            spec(specJsonPlace).
                            when().
                            get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertions

        response.
                then().
                assertThat().
                statusCode(200).
                body("title",Matchers.equalTo("optio dolor molestias sit"));

    }

    @Test
    public void test03(){
        //     3- https://jsonplaceholder.typicode.com/posts/50 endpointine
        //    bir DELETE request gonderdigimizde donen response’un status code’unun 200
        //    oldugunu ve response body’sinin null oldugunu test edin

        // 1- request url ve body olustur
        specJsonPlace.pathParams("pp1","posts","pp2",50);

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonderip donen sonucu kaydet

        Response response=given().
                            spec(specJsonPlace).
                            when().
                            delete("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- Assertions

        response.
                then().
                assertThat().
                statusCode(200).
                body("id",Matchers.nullValue());

    }


}
