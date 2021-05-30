package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C4_Put_ResponseBilgileriAssertion {

    @Test
    public void putMetot(){
        /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
                 */

        // 1- request icin gerekli url ve body'yi olustur

        String url = "https://jsonplaceholder.typicode.com/posts/70";
        JSONObject reqBody = new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);
        // 2- Soruda verilmisse expected body olustur
        // 3- Response'i olustur ve kaydet

        Response response= given().
                                contentType(ContentType.JSON).
                                when().
                                body(reqBody.toString()).
                                put(url);
        // 4- Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                header("Server","cloudflare").
                statusLine("HTTP/1.1 200 OK");


    }
}
