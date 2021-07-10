package TestDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    public int basariliStatusCode=200;
    public String contentType="application/json; charset=utf-8";
    public String connectionHeaderDegeri="keep-alive";

    public JSONObject expctedDataOlustur(){

        JSONObject expectedDataJson=new JSONObject();

        expectedDataJson.put("userId",3);
        expectedDataJson.put("id",22);
        expectedDataJson.put("title","dolor sint quo a velit explicabo quia nam");
        expectedDataJson.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

            return expectedDataJson;
    }

    public JSONObject putRequestBodyOlustur() {
        JSONObject requestBody=new JSONObject();
        requestBody.put("userId",10);
        requestBody.put("id",70);
        requestBody.put("title","Ahmet");
        requestBody.put("body","Merhaba");

        return requestBody;
    }

    public JSONObject putRequestExpectedBodyOlustur() {
        JSONObject putRequestExpectedBodyJson=new JSONObject();
        putRequestExpectedBodyJson.put("userId",10);
        putRequestExpectedBodyJson.put("id",70);
        putRequestExpectedBodyJson.put("title","Ahmet");
        putRequestExpectedBodyJson.put("body","Merhaba");

        return putRequestExpectedBodyJson;
    }

    public HashMap requestBodyMapOlustur(){

        /*
        {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
         */

        HashMap<String,Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("title","Ahmet");
        requestBodyMap.put("body","Merhaba");
        requestBodyMap.put("userId",10.0);
        requestBodyMap.put("id",70.0);

        return requestBodyMap;
    }

    public HashMap requestBodyMapOlustur(String title,String body,double userId,double id){
        HashMap<String,Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("title",title);
        requestBodyMap.put("body",body);
        requestBodyMap.put("userId",userId);
        requestBodyMap.put("id",id);

        return requestBodyMap;
    }



}
