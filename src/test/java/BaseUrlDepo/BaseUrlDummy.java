package BaseUrlDepo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlDummy {
    public RequestSpecification specDummyRestApi;

    @Before
    public void setup(){

        specDummyRestApi=new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
}
