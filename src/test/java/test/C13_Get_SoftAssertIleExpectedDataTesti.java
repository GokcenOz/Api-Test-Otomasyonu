package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {
    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
        donen response’un asagidaki gibi oldugunu test edin.
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
    public void get01(){
        // 1- request icin url ve body olustur
        String url ="http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- soruda isteniyorsa expected data olustur
        JSONObject expData= new JSONObject();
        JSONObject dataBody=new JSONObject();

        dataBody.put("id",3);
        dataBody.put("employee_name","Ashton Cox");
        dataBody.put("employee_salary",86000);
        dataBody.put("employee_age",66);
        dataBody.put("profile_image","");

        expData.put("status","success");
        expData.put("data",dataBody);
        expData.put("message","Successfully! Record has been fetched.");

        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().when().get(url);
        response.prettyPrint();

        // 4- Assertion
        // Response'i JsonPath objesine cevirelim
        JsonPath respJPath=response.jsonPath();
            // - softAssert objesi olustur
        SoftAssert softAssert=new SoftAssert();

        // istedigimiz tum assertion'lari soft assert ile yapin
        softAssert.assertEquals(respJPath.get("status"),expData.get("status"),"status testi calismadi");
        softAssert.assertEquals(respJPath.get("data.id"),expData.getJSONObject("data").get("id"));
        softAssert.assertEquals(respJPath.get("data.employee_name"),expData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(respJPath.get("data.employee_salary"),expData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(respJPath.get("data.employee_age"),expData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(respJPath.get("data.profile_image"),expData.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(respJPath.get("message"),expData.get("message"));

        // - softAssert'in raporlama yapmasi icin assertAll olusturun
        softAssert.assertAll();

    }
}
