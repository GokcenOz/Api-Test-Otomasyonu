package test;

import BaseUrlDepo.BaseUrlDummy;
import TestDataDeposu.TestDataDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends BaseUrlDummy {

    @Test
    public void test01() {

        //  http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
        //    donen response’un status code’unun 200, content Type’inin application/json
        //    ve body’sinin asagidaki gibi oldugunu test edin.
        //        Response Body
        //        {
        //            "status":"success",
        //            "data":{
        //                    "id":3,
        //                    "employee_name":"Ashton Cox",
        //                    "employee_salary":86000,
        //                    "employee_age":66,
        //                    "profile_image":""
        //                    },
        //            "message":"Successfully! Record has been fetched."
        //        }
        //     */

        //1- request url ve body olustur

        specDummyRestApi.pathParams("pp1","employee","pp2",3);

        // 2- soruda isteniyorsa expected data olustur
        TestDataDummyRestApi testDataDummyRestApi=new TestDataDummyRestApi();
        JSONObject expectedData=testDataDummyRestApi.getRequestExpectedDataOlustur();

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                                spec(specDummyRestApi).
                                when().
                                get("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4- Assertion

        JsonPath responseJPath=response.jsonPath();

        assertEquals(testDataDummyRestApi.basariliStatusCode,response.getStatusCode());
        assertEquals(testDataDummyRestApi.contentType,response.getContentType());
        assertEquals(expectedData.getString("status"),responseJPath.getString("status"));
        assertEquals(expectedData.getJSONObject("data").getInt("id"),responseJPath.getInt("data.id"));
        assertEquals(expectedData.getJSONObject("data").getString("employee_name"),responseJPath.getString("data.employee_name"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),responseJPath.getInt("data.employee_salary"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),responseJPath.getInt("data.employee_age"));
        assertEquals(expectedData.getJSONObject("data").getString("profile_image"),responseJPath.getString("data.profile_image"));
        assertEquals(expectedData.getString("message"),responseJPath.getString("message"));






    }
}
