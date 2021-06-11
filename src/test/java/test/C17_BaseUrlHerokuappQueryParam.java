package test;

import BaseUrlDepo.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void test01(){
        /*
        1-  https://restful-booker.herokuapp.com/booking endpointine
         bir GET request gonderdigimizde donen response’un
         status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin

         */

        // 1- Request url ve body sini olustur

        specHerokuapp.pathParam("pp1","booking");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                                spec(specHerokuapp).
                                when().
                                get("/{pp1}");

        response.prettyPrint();

        // Assertions

        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid", Matchers.hasSize(22));

    }

    @Test
    public void test02(){
        /*
        2- https://restful-booker.herokuapp.com/booking endpointine
        gerekli Query parametrelerini yazarak
        “firstname” degeri “Eric” olan rezervasyon oldugunu test edecek
        bir GET request gonderdigimizde, donen response’un
        status code’unun 200 oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin

         */

        // 1- request url ve body olustur

        specHerokuapp.pathParam("pp1","booking").queryParam("firstname","Eric");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet

        Response response=given().
                                spec(specHerokuapp).
                                when().
                                get("/{pp1}");

        response.prettyPrint();

        // 4- assertions

        response.
                then().
                assertThat().
                statusCode(200).
                body("bookingid",Matchers.hasSize(2));

        Assert.assertTrue(response.asString().contains("bookingid"));

        System.out.println(response.asString());
    }

    @Test
    public void test03(){
        /*
            3- https://restful-booker.herokuapp.com/booking endpointine
            gerekli Query parametrelerini yazarak “firstname” degeri “Eric”
            ve “lastname” degeri “Jones” olan rezervasyon
            oldugunu test edecek bir GET request gonderdigimizde,
            donen response’un status code’unun 200 oldugunu
            ve “Eric Jones” ismine sahip en az bir booking oldugunu test edin
         */

        // 1- request url ve body olustur

        specHerokuapp.
                pathParam("pp1","booking").
                queryParams("firstname","Eric","lastname","Jones");

        // 2- Soruda varsa expected data olustur
        // 3- Response olustur, request gonder ve donen response'u kaydet
        Response response=given().
                            spec(specHerokuapp).
                            when().
                            get("/{pp1}");
        response.prettyPrint();

        // Assertion

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertTrue(response.asString().contains("bookingid"));
    }
}
