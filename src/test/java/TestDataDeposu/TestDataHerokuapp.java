package TestDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestDataHerokuapp {

    public JSONObject getRuestBodyOlustur(){

        JSONObject requestBody = new JSONObject();
        JSONObject innerBody = new JSONObject();

        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates",innerBody);
        requestBody.put("additionalneeds", "wi-fi");

        return requestBody;
    }

    public JSONObject postRequestExpectedDataOlustur(){

        JSONObject expectedData = new JSONObject();
        JSONObject bookingBody = new JSONObject();
        JSONObject bookingdatesBody = new JSONObject();

        bookingdatesBody.put("checkin", "2021-06-01");
        bookingdatesBody.put("checkout", "2021-06-10");

        bookingBody.put("firstname", "Ahmet");
        bookingBody.put("lastname", "Bulut");
        bookingBody.put("totalprice", 500);
        bookingBody.put("depositpaid", false);
        bookingBody.put("bookingdates",bookingdatesBody);
        bookingBody.put("additionalneeds", "wi-fi");

        expectedData.put("bookingid",24);
        expectedData.put("booking",bookingBody);



        return expectedData;
    }

    public Map<String,Object> requestBodyMapOlustur(){
        /*
             Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }
    	                     */
        Map<String,Object> requestBodyMap=new HashMap<>();
            requestBodyMap.put("firstname","Ahmet");
            requestBodyMap.put("lastname","Bulut");
            requestBodyMap.put("totalprice",500);
            requestBodyMap.put("depositpaid",false);
            requestBodyMap.put("bookingdates",bookingdatesMapOlustur());
            requestBodyMap.put("additionalneeds","wi-fi");
        return requestBodyMap;
    }
    public   Map<String,Object> bookingdatesMapOlustur(){
        Map<String,Object> bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin","2021-06-01");
        bookingdatesMap.put("checkout","2021-06-10");
        return bookingdatesMap;
    }

    public Map<String,Object> expectedDataMapOlustur(){
        /*
        Response Body // expected data
    	            {
                    "bookingid":24,
                    "booking":{  // bookingMap biraz once olusturdugumuz requestBody ile tamamen ayni
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                        ,
                        "additionalneeds":"wi-fi"
                    }
                  */
        // Expected datada icice 3 map ve toplam 9 attribute var ama biz onceki olusturdugumuz metodlari kullanarak
        // sadece 3 SATIRDA expected datayi olusturduk
        Map<String,Object> expectedDataMap=new HashMap<>();
        expectedDataMap.put("bookingid",24);
        expectedDataMap.put("booking",requestBodyMapOlustur());
        return expectedDataMap;
    }















}
