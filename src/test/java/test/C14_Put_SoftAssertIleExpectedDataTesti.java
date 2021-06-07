package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {
    /*
    http://dummy.restapiexample.com/api/v1/update/21 url’ine
    asagidaki body’ye sahip bir PUT request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.

    Request Body
            {
            "status": "success",
            "data": {
                "name": "Ahmet",
                "salary": "1230",
                "age": "44",
                "id": 40
                    }
           }

    Response Body

            {
            "status": "success",
            "data": {
                "status": "success",
                "data": {
                    "name": "Ahmet",
                    "salary": "1230",
                    "age": "44",
                    "id": 40
                }
            },
            "message": "Successfully! Record has been updated."
        }

     */

    @Test
    public void put01(){
        // 1- request url ve body olustur
        String url ="http://dummy.restapiexample.com/api/v1/update/21";
        JSONObject reqBody=new JSONObject();
        JSONObject dataBody= new JSONObject();

        dataBody.put("name", "Ahmet");
        dataBody.put("salary", "1230");
        dataBody.put("age", "44");
        dataBody.put("id", 40);

        reqBody.put("status", "success");
        reqBody.put("data",dataBody);

        // 2- Soruda isteniyorsa Expected Datayi olustur

        JSONObject expData=new JSONObject();
        JSONObject dataDisBody=new JSONObject();
        JSONObject dataIcBody=new JSONObject();

        dataIcBody.put("name", "Ahmet");
        dataIcBody.put("salary", "1230");
        dataIcBody.put("age", "44");
        dataIcBody.put("id", 40);

        dataDisBody.put("status","success");
        dataDisBody.put("data",dataIcBody);

        expData.put("status","success");
        expData.put("data",dataDisBody);
        expData.put("message", "Successfully! Record has been updated.");

        // 3- Response olustur, request gonder ve donen response'i kaydet

        Response response=given().
                                contentType(ContentType.JSON).
                                when().
                                body(reqBody.toString()).
                                put(url);

        response.prettyPrint();


        // 4- Assertions
        JsonPath resJPath=response.jsonPath(); // response'i JsonPath objesine cevirdim

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(resJPath.get("status"),expData.get("status"));
        softAssert.assertEquals(resJPath.get("data.data.name"),expData.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(resJPath.get("data.data.id"),expData.getJSONObject("data").getJSONObject("data").get("id"));
        softAssert.assertEquals(resJPath.get("data.data.salary"),expData.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(resJPath.get("data.data.age"),expData.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(resJPath.get("data.status"), expData.getJSONObject("data").get("status"));
        softAssert.assertEquals(resJPath.get("message"),expData.get("message"));

        softAssert.assertAll();
    }
}
