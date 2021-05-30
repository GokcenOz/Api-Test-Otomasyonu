package test;

import org.json.JSONObject;
import org.junit.Test;

public class C3_JsonObjesiOlusturma {

    @Test
    public void jsonObje01(){
        /*
        {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":1
            }
         */

        JSONObject ilkJsonObje = new JSONObject();
        ilkJsonObje.put("title","Ahmet");
        ilkJsonObje.put("body","Merhaba");
        ilkJsonObje.put("userId",1);

        System.out.println(ilkJsonObje);

        /*
        {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":1
            }
         */
    }

    @Test
    public void jsonObje2(){
        /*
                {
                 "firstname":"Jim",
                 "additionalneeds":"Breakfast",
                 "bookingdates":{
                         "checkin":"2018-01-01",
                         "checkout":"2019-01-01"
                    },
                  "totalprice":111,
                  "depositpaid":true,
                  "lastname":"Brown"
                    }

         */

        JSONObject ikinciJsonObje = new JSONObject();
        JSONObject innerJson= new JSONObject();

        innerJson.put("checkin","2018-01-01");
        innerJson.put("checkout","2019-01-01");

        ikinciJsonObje.put("firstname","Jim");
        ikinciJsonObje.put("additionalneeds","Breakfast");
        ikinciJsonObje.put("bookingdates",innerJson);
        ikinciJsonObje.put("totalprice",111);
        ikinciJsonObje.put("depositpaid",true);
        ikinciJsonObje.put("lastname","Brown");

        System.out.println(ikinciJsonObje);
    }
}
