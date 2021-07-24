package test;

import BaseUrlDepo.BaseUrlHerokuapp1;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoBookingBody;
import pojos.PojoBookingDates;
import pojos.PojoHerokuappExpectedData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Post_Pojo extends BaseUrlHerokuapp1 {
     /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.
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

    	            	Response Body // expected data
    	            {
                    "bookingid":24,
                    "booking":{
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

    @Test
    public void test01(){

        // 1- request url ve body olustur
        specHerokuapp.pathParam("pp1","booking");
        // body icin pojo class olusturalim
        PojoBookingDates pojoBookingDates= new PojoBookingDates("2021-06-01","2021-06-10");
        PojoBookingBody requestBodyPojo= new PojoBookingBody("Ahmet","Bulut",500,false,pojoBookingDates,"wi-fi");

        // 2- soruda varsa expected data olustur

        // expected data pojo olusturmak icin en icerdeki pojo;dan baslamaliyim
        PojoBookingDates pojoBookingDates1= new PojoBookingDates("2021-06-01","2021-06-10");
        PojoBookingBody expectedDataBookingBody=new PojoBookingBody(
                "Ahmet","Bulut",500,false,pojoBookingDates1,"wi-fi"   );
        PojoHerokuappExpectedData expectedDataPojo = new PojoHerokuappExpectedData(
                24,expectedDataBookingBody    );

        System.out.println(expectedDataPojo);

        /*
        PojoHerokuappExpectedData
         {bookingid=24, booking= PojoBookingBody
                        {firstname='Ahmet',
                        lastname='Bulut',
                        totalprice=500,
                        depositpaid=false, bookingdates=PojoBookingDates
                                                        {checkin='"2021-06-01',
                                                         checkout='2021-06-10'},
                        additionalneeds='wi-fi'}}

         */

        // 3 - Response olustur, request gonderip sonucu response'a ata

        Response response= given().
                                contentType(ContentType.JSON).
                                spec(specHerokuapp).
                                when().
                                body(requestBodyPojo).
                                post("/{pp1}");
        response.prettyPrint();

        // 4- assertion
        // expectedDataPojo ==> response

        PojoHerokuappExpectedData responsePojo = response.as(PojoHerokuappExpectedData.class);
        System.out.println(responsePojo);
        // expectedDataPojo ==> responsePojo

        assertEquals(expectedDataPojo.getBooking().getFirstname(),responsePojo.getBooking().getFirstname());
        assertEquals(expectedDataPojo.getBooking().getLastname(),responsePojo.getBooking().getLastname());
        assertEquals(expectedDataPojo.getBooking().getTotalprice(),responsePojo.getBooking().getTotalprice());
        assertEquals(expectedDataPojo.getBooking().isDepositpaid(),responsePojo.getBooking().isDepositpaid());
        assertEquals(expectedDataPojo.getBooking().getBookingdates().getCheckin(),responsePojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBooking().getBookingdates().getCheckout(),responsePojo.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedDataPojo.getBooking().getAdditionalneeds(),responsePojo.getBooking().getAdditionalneeds());

    }
}
