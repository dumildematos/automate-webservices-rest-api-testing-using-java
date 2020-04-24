package org.example;

import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

public class WheatherGetRequest {

    //Testing response status 200
    //@Test
    public void Test_01() {
        Response resp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");
        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    //Testing response status 401
    //@Test
    public void Test_02() {
        Response resp = when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a0");
        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 401);
    }

    // Use parameters with rest assured
    //@Test
    public void Test_03() {
        Response resp = given().
                param("q","London").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");
        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);

        if(resp.getStatusCode() == 200){
            System.out.println("API has connected well");
        }else {
            System.out.println("API has a connection situation");
        }
    }

    // Asset testcase in Rest assured api
    //@Test
    public void Test_04() {
            given().
            param("q","London").
            param("appid","439d4b804bc8187953eb36d2a8c26a02").
            when().
            get("https://samples.openweathermap.org/data/2.5/weather").
            then().assertThat().statusCode(200);
    }

    //@Test
    public void Test_05(){

        Response resp = given().
                param("q","London").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        System.out.println(resp.asString());

    }

    @Test
    public void Test_06() {
        Response resp = given().
                param("zip","201010,in").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
}
