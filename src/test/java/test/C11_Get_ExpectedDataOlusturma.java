package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C11_Get_ExpectedDataOlusturma {

    @Test
    public void getTesti(){
        /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda
        donen response body’sinin asagida verilen ile ayni oldugunutest ediniz

        {
            "userId":3,
            "id":22,
            "title":"dolor sint quo a velit explicabo quia nam",
            "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                         um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
         */

        // 1 - Request icin url ve body olustur

        String url= "https://jsonplaceholder.typicode.com/posts/22";

        // 2- Expected data olustur
        JSONObject expData =new JSONObject();
        expData.put("userId",3);
        expData.put("id",22);
        expData.put("title","dolor sint quo a velit explicabo quia nam");
        expData.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Assertion

        // oncelikle response'i JsonPath objesine cevirmeliyiz
        JsonPath respJPath =response.jsonPath();


        Assert.assertEquals("userId testi calismadi",expData.get("userId"),respJPath.get("userId"));
        Assert.assertEquals(expData.get("id"),respJPath.get("id"));
        Assert.assertEquals(expData.get("title"),respJPath.get("title"));
        Assert.assertEquals(expData.get("body"),respJPath.get("body"));
    }
}
