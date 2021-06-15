package TestDataDeposu;

import org.json.JSONObject;

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
}
