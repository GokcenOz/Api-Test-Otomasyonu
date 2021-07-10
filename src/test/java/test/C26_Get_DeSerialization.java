package test;

import BaseUrlDepo.BaseUrlDummy;
import TestDataDeposu.TestDataDummyRestApi;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Get_DeSerialization extends BaseUrlDummy {
           /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.
        Response Body // expected data
        {
            "status":"success",
            "data":{
                    "id":3,
                    "employee_name":"Ashton Cox",
                    "employee_salary":86000,
                    "employee_age":66,
                    "profile_image":""
                    },
            "message":"Successfully! Record has been fetched."
        }
     */

    @Test
    public void test01(){
        // 1- request body ve url olustur
        specDummyRestApi.pathParams("pp1","employee","pp2",3);
        // 2- Soruda varsa expected data olustur
        TestDataDummyRestApi testDataDummyRestApi=new TestDataDummyRestApi();
        HashMap<String,Object> expectedDataMap= testDataDummyRestApi.expectedDataMapOlustur(3,"Ashton Cox"
        ,86000.0,66.0,"","success","Successfully! Record has been fetched.");

        System.out.println(expectedDataMap);
        // {
        // data={
        //          profile_image=,
        //          employee_name=Ashton Cox,
        //          employee_salary=86000.0,
        //          id=3.0,
        //          employee_age=66},
        // message=Successfully! Record has been fetched.,
        // status=success}

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().
                                spec(specDummyRestApi).
                                when().
                                get("/{pp1}/{pp2}");

        response.prettyPrint();
        HashMap responseMap=response.as(HashMap.class);
        System.out.println(responseMap);
        // { data={
        //      id=3.0,
        //      employee_name=Ashton Cox,
        //      employee_salary=86000.0,
        //      employee_age=66.0,
        //      profile_image=},
        // message=Successfully! Record has been fetched.,
        // status=success}

        // 4- Assertion

        assertEquals(((Map)expectedDataMap.get("data")).get("profile_image"), ((Map)responseMap.get("data")).get("profile_image")     );
        assertEquals( ((Map) expectedDataMap.get("data")).get("employee_name"), ((Map) responseMap.get("data")).get("employee_name"));
        assertEquals( ((Map) expectedDataMap.get("data")).get("employee_salary"), ((Map) responseMap.get("data")).get("employee_salary"));
        assertEquals( ((Map) expectedDataMap.get("data")).get("id"), ((Map) responseMap.get("data")).get("id"));
        assertEquals( ((Map) expectedDataMap.get("data")).get("employee_age"), ((Map) responseMap.get("data")).get("employee_age"));
        assertEquals(expectedDataMap.get("message"), responseMap.get("message"));
        assertEquals(expectedDataMap.get("status"),responseMap.get("status"));

    }

}
