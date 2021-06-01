package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C7_Get_BodyTekrarlardanKurtulma {

    @Test
    public void get01(){
        /*
                https://restful-booker.herokuapp.com/booking/10 url’ine

                bir GET request gonderdigimizde donen Response’un,
                status code’unun 200,
                ve content type’inin application-json,
                ve response body’sindeki
                    "firstname“in,"Susan",
                    ve "lastname“in, "Jackson",
                    ve "totalprice“in,612,
                    ve "depositpaid“in,false,
                    ve "additionalneeds“in,"Breakfast"
                oldugunu test edin
         */

        // 1- request url ve body'sini olusturalim

        String url = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Soruda varsa expected data olustur
        // 3- Response objesi olustur ve request'i gonderip response'i kaydet

        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Assertion
        /*
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Jim")).
                body("lastname",Matchers.equalTo("Jones")).
                body("totalprice",Matchers.equalTo(126)).
                body("depositpaid",Matchers.equalTo(true)).
                body("additionalneeds",Matchers.equalTo("Breakfast"));

        // once boddy'lerden kurtulalim
        response.
                then().
                assertThat().
                body("firstname",Matchers.equalTo("Mark"),
                        "lastname",Matchers.equalTo("Smith"),
                        "totalprice",Matchers.equalTo(650),
                        "depositpaid",Matchers.equalTo(true),
                        "additionalneeds",Matchers.equalTo("Breakfast"));
        */
        // Matchers'dan kurtulalim
        // yukaridaki Matchers'larin silinmemei icin onlari yorum satiri haline getiriyorum

        response.
                then().
                assertThat().
                body("firstname",equalTo("Mark"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(650),
                        "depositpaid", equalTo(true),
                        "additionalneeds",equalTo("Breakfast"));

    }
}
