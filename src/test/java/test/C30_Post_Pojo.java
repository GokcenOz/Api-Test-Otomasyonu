package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.havaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C30_Post_Pojo {
    /*
     https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0 url’ine
     bir post request gonderdigimizde donen response’un asagidaki body’ye sahip oldugunu test ediniz

                        {
                    "coord": {
                        "lon": -0.1257,
                        "lat": 51.5085
                    },
                    "weather": [
                        {
                            "id": 804,
                            "main": "Clouds",
                            "description": "overcast clouds",
                            "icon": "04d"
                        }
                    ],
                    "base": "stations",
                    "main": {
                        "temp": 291.99,
                        "feels_like": 292.18,
                        "temp_min": 289.89,
                        "temp_max": 293.71,
                        "pressure": 1007,
                        "humidity": 86
                    },
                    "visibility": 8000,
                    "wind": {
                        "speed": 1.54,
                        "deg": 0
                    },
                    "clouds": {
                        "all": 90
                    },
                    "dt": 1627206846,
                    "sys": {
                        "type": 2,
                        "id": 2006068,
                        "country": "GB",
                        "sunrise": 1627186441,
                        "sunset": 1627243183
                    },
                    "timezone": 3600,
                    "id": 2643743,
                    "name": "London",
                    "cod": 200
                }
     */

    @Test
    public void test01(){

        // 1- request url ve body olustur
        String url= " https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0";

        // 2- Soruda varsa expected data olustur
        Coord coordPojo=new Coord(-0.1257F,51.5085f);
        Weather weatherPojo=new Weather(804,"Clouds","overcast clouds","04d");
        List<Weather> weatherList =new ArrayList<>();
        weatherList.add(weatherPojo);
        Main mainPojo = new Main(291.99f,292.18f,289.89f,293.71f,1007,86);
        Wind windPojo =new Wind(1.54f,0);
        Clouds cloudsPojo =new Clouds(90);
        Sys sysPojo= new Sys(2,2006068,"GB",1627186441,1627243183);

        HavaDurumuLondon expectedDataPojo= new HavaDurumuLondon(
             coordPojo, weatherList,"stations", mainPojo,9000,windPojo,cloudsPojo,1627206846,
                sysPojo,3600,2643743,"London",200  );
        // 3- Response olustur, request gonderip donen response'i kaydet

        Response response=given().when().post(url);

        // 4- assert
        HavaDurumuLondon responsePojo = response.as(HavaDurumuLondon.class);
        System.out.println(responsePojo); // null degerler dondurdugu icin bunu kullanmiyoruz

        // expectedDataPojo ==> responseJsonPath
        JsonPath responseJsonpath= response.jsonPath();

        Assert.assertEquals(expectedDataPojo.getSys().getCountry(),responseJsonpath.get("sys.country"));
        Assert.assertEquals(expectedDataPojo.getName(),responseJsonpath.get("name"));
        Assert.assertEquals(expectedDataPojo.getCoord().getLat(),responseJsonpath.get("coord.lat"));


    }
}
