package test;

import BaseUrlDepo.BaseUrlJsonPlaceHolder;
import TestDataDeposu.TestDataJsonPlaceHolder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;



import static io.restassured.RestAssured.given;

public class C18_Get_TestPojoDummyDataClassKullanimi extends BaseUrlJsonPlaceHolder {
     /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda donen response’in status kodunun 200
        ve response body’sinin asagida verilen ile ayni oldugunutest ediniz

        Response body :
        {
            "userId":3,
            "id":22,
            "title":"dolor sint quo a velit explicabo quia nam",
            "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */

    @Test
    public void test01(){
        // 1 - Request url ve body'sini olustur
        specJsonPlace.pathParams("pp1","posts","pp2",22);
        // 2- Soruda varsa expected Data olustur

        TestDataJsonPlaceHolder testDataJsonPlaceHolder =new TestDataJsonPlaceHolder();

        JSONObject expectedDataJson = testDataJsonPlaceHolder.expctedDataOlustur();

        System.out.println(expectedDataJson);


        // {
        //  "id":22,
        //  "title":"dolor sint quo a velit explicabo quia nam",
        //  "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse",
        //  "userId":3
        //  }

        // 3- Response olustur, requesti gonderip donen response'i kaydet
        Response response=given().
                                spec(specJsonPlace).
                                when().
                                get("{pp1}/{pp2}");

        response.prettyPrint();


        // 4- assertions
        // suana kadar 2 assertion yontemi ogrendik
            // response.then().assertThat....  response ait temel bilgileri test edebiliyorduk,
            // body'deki degerler icin de Matchers Class'indan yardim aliyorduk

            // Assert ve softAssert yontemleriyle test yapabiliriz

        JsonPath responseJPath=response.jsonPath();


        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(expectedDataJson.getInt("userId"),responseJPath.getInt("userId"));
        Assert.assertEquals(expectedDataJson.getInt("id"),responseJPath.getInt("id"));
        Assert.assertEquals(expectedDataJson.getString("title"),responseJPath.getString("title"));
        Assert.assertEquals(expectedDataJson.getString("body"),responseJPath.getString("body"));


    }
}
