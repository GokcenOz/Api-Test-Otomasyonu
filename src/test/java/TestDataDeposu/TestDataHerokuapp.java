package TestDataDeposu;

import org.json.JSONObject;

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
}
