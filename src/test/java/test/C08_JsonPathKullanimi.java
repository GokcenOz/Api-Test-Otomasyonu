package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JsonPathKullanimi {

    @Test
    public void jsonObjesiOlusturma(){

        JSONObject kisiBilgisi=new JSONObject();
        JSONObject adresBilgisi=new JSONObject();
        JSONObject cepTelefonu=new JSONObject();
        JSONObject evTel= new JSONObject();
        JSONArray telBilgileri =new JSONArray();


        // once tel bilgilerini iki JSON objesine ekliyorum

        cepTelefonu.put("type","Cep Telefonu");
        cepTelefonu.put("number", "555-123-4567");

        evTel.put("type","Ev telefonu");
        evTel.put("number","312-123-4567");

        telBilgileri.put(cepTelefonu);
        telBilgileri.put(evTel);

        // adres JSON Objesini olusturalim
        adresBilgisi.put("streetAddress", "Yenimahalle kurtulus cad");
        adresBilgisi.put("city","Ankara");
        adresBilgisi.put("postalCode","06100");

        kisiBilgisi.put("firstName","Ahmet");
        kisiBilgisi.put("lastName","Bulut");
        kisiBilgisi.put("age",49);
        kisiBilgisi.put("address",adresBilgisi);
        kisiBilgisi.put("phoneNumbers",telBilgileri);

        System.out.println(kisiBilgisi);

        /*
        {
        "firstName":"Ahmet",
        "lastName":"Bulut",
        "address":{
            "streetAddress":"Yenimahalle kurtulus cad",
            "city":"Ankara",
            "postalCode":"06100"},
        "age":49,
        "phoneNumbers":[
            {
            "number":"555-123-4567",
            "type":"Cep Telefonu"},
            {
            "number":"312-123-4567",
            "type":"Ev telefonu"}
            ]
        }

         */

        // OLusturdugumuz komplex JSON objesindeki degerlere NASIL ULASABILIRIM ?

        System.out.println("Isim : " + kisiBilgisi.get("firstName"));
        System.out.println("Soyisim : " + kisiBilgisi.get("lastName"));
        System.out.println("Yas : " + kisiBilgisi.get("age"));

        System.out.println("Cadde adresi : "+ kisiBilgisi.getJSONObject("address").get("streetAddress"));
        System.out.println("Sehir : " + kisiBilgisi.getJSONObject("address").get("city"));
        System.out.println("Posta kodu : " + kisiBilgisi.getJSONObject("address").get("postalCode"));

        System.out.println("Cep tel No : " + kisiBilgisi.
                                            getJSONArray("phoneNumbers").
                                            getJSONObject(0).
                                            get("number"));


        System.out.println("Ev tel no : " + kisiBilgisi.
                                                    getJSONArray("phoneNumbers").
                                                    getJSONObject(1).
                                                    get("number"));



    }
}
