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

public class C23_Get_DeSerialization extends BaseUrlDummy {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
    donen response’un status code’unun 200, content Type’inin application/json
    ve body’sinin asagidaki gibi oldugunu test edin.
        Response Body
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
        // 1- request url ve body olustur
        specDummyRestApi.pathParams("pp1","employee","pp2",3);

        // 2- soruda varsa expected data olustur

        TestDataDummyRestApi testDataDummyRestApi=new TestDataDummyRestApi();
        HashMap<String,Object> expectedData= testDataDummyRestApi.expectedDataMapOlustur();

        // 3- response olustur ve kaydet

        Response response= given().
                                spec(specDummyRestApi).
                                when().
                                get("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4-Assert
        // expectedDataMap == response
        HashMap<String,Object> responseMap = response.as(HashMap.class);

        System.out.println("expected data : " + expectedData);
        /*
        expected data : ic ice Map olarak olusturduk
        {data = {profile_image=, employee_name=Ashton Cox, employee_salary=86000, id=3.0, employee_age=66},
         message=Successfully! Record has been fetched.,
         status=success}

        response Map : donen response'i as() metodu ile map'e cevirdik
        {data={id=3.0, employee_name=Ashton Cox, employee_salary=86000.0, employee_age=66.0, profile_image=},
        message=Successfully! Record has been fetched.,
        status=success}
        */
        System.out.println("response Map : " + responseMap);
        // exdectedDataMap ==== responseMap

        assertEquals(expectedData.get("status"),responseMap.get("status"));
        assertEquals(((Map)expectedData.get("data")).get("id"),((Map)responseMap.get("data")).get("id"));
        assertEquals(  ((Map)expectedData.get("data")).get("employee_name"), ((Map) responseMap.get("data")).get("employee_name")     );
        assertEquals(  ((Map) expectedData.get("data")).get("employee_salary"), ((Map) responseMap.get("data")).get("employee_salary") );
    }
}
