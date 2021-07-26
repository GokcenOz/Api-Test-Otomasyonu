package test;

import BaseUrlDepo.BaseUrlDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummy;
import pojos.PojoDummyData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_Get_Pojo extends BaseUrlDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine
    bir GET request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

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
        // 1- request url ve body olustur

        specDummyRestApi.pathParams("pp1","employee","pp2",3);

        // 2- soruda varsa expected data olustur
        PojoDummyData pojoDummyData = new PojoDummyData(3,"Ashton Cox",86000,66,"");
        PojoDummy expectedDataPojo= new PojoDummy("success",pojoDummyData,"Successfully! Record has been fetched.");
        System.out.println(expectedDataPojo);

        /*
        pojos.PojoDummy@3e11f9e9
        [ status=success,
        data=pojos.PojoDummyData@1de5f259 [ id=3,
            employeeName=Ashton Cox,
            employeeSalary=86000,
            employeeAge=66,
            profileImage=,
            additionalProperties={}],
         message=Successfully! Record has been fetched.,
         additionalProperties={}]
         */

        // 3- Response olustur, request gonderip, sonucu olusturdugumuz response'a ata
        Response response = given().spec(specDummyRestApi).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4- assert
        // expectedDataPojo ==> response
        PojoDummy responsePojo=response.as(PojoDummy.class);
        System.out.println(responsePojo);
        // Pojo class'lari web sitelerinden yardim alarak olusturdugumuzda
        // attribute isimlerini degistirdiginden response'i convert etmek istedigimizde
        // inner pojo'lari olusturmada sorunlar cikabilir

        // bu sorunun cozumu icin
        // komplex response body'lerinde alt attribute'lere ulasmanin en kolay yolu olan
        // JsonPath kullaniriz

        JsonPath responseJsonPath= response.jsonPath();
        // expectedDataPojo ==> responseJsonPath

        assertEquals(expectedDataPojo.getStatus(),responseJsonPath.get("status"));
        assertEquals(expectedDataPojo.getData().getId(),responseJsonPath.get("data.id"));
        assertEquals(expectedDataPojo.getData().getEmployeeName(),responseJsonPath.get("data.employee_name"));
        assertEquals(expectedDataPojo.getData().getEmployeeSalary(),responseJsonPath.get("data.employee_salary"));
        assertEquals(expectedDataPojo.getData().getEmployeeAge(),responseJsonPath.get("data.employee_age"));
        assertEquals(expectedDataPojo.getData().getProfileImage(),responseJsonPath.get("data.profile_image"));
        assertEquals(expectedDataPojo.getMessage(),responseJsonPath.get("message"));

    }
}
