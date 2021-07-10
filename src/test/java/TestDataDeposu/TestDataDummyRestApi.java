package TestDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataDummyRestApi {
    public int basariliStatusCode=200;
    public String contentType="application/json";

    public JSONObject getRequestExpectedDataOlustur(){

        JSONObject expectedData = new JSONObject();
        JSONObject dataBody=new JSONObject();
        dataBody.put("id",3);
        dataBody.put("employee_name", "Ashton Cox");
        dataBody.put("employee_salary",86000);
        dataBody.put("employee_age",66);
        dataBody.put("profile_image","");

        expectedData.put("status","success");
        expectedData.put("data",dataBody);
        expectedData.put("message","Successfully! Record has been fetched.");


        return expectedData;
    }

    public HashMap<String,Object> expectedDataMapOlustur(){
        /*
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

        HashMap<String,Object> expectedDataMap=new HashMap<>();
        HashMap<String,Object> innerMap=new HashMap<>();

        innerMap.put("id",3.0);
        innerMap.put("employee_name","Ashton Cox");
        innerMap.put("employee_salary",86000.0);
        innerMap.put("employee_age",66);
        innerMap.put("profile_image","");

        expectedDataMap.put("status","success");
        expectedDataMap.put("data",innerMap);
        expectedDataMap.put("message","Successfully! Record has been fetched.");


        return expectedDataMap;

    }
    public HashMap<String,Object> expectedDataMapOlustur(double id,String employee_name,
                                                         double employee_salary,double employee_age,
                                                        String profile_image, String status,String message){

        HashMap<String,Object> expectedDataMap=new HashMap<>();
        HashMap<String,Object> innerMap=new HashMap<>();

        innerMap.put("id",id);
        innerMap.put("employee_name",employee_name);
        innerMap.put("employee_salary",employee_salary);
        innerMap.put("employee_age",employee_age);
        innerMap.put("profile_image",profile_image);

        expectedDataMap.put("status", status);
        expectedDataMap.put("data",innerMap);
        expectedDataMap.put("message",message);


        return expectedDataMap;

    }
}
