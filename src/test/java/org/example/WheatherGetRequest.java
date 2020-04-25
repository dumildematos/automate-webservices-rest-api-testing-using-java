package org.example;

import com.jayway.restassured.http.ContentType;
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
    //@Test
    public void Test_06() {
        Response resp = given().
                param("id","2172797").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }
    //@Test
    public void Test_07() {
        Response resp = given().
                param("zip","201010,in").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    //@Test
    public void Test_08() {
        String weatherReport = given().
                param("id","2172797").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather").
                then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        System.out.println("The weather report for today is: " + weatherReport );
    }

    //@Test
    public void Test_09() {
        Response resp = given().
                param("id","2172797").
                param("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String actualWeatherReport = resp.then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        String expectedWeatherReport = null;

        if(actualWeatherReport.equalsIgnoreCase(expectedWeatherReport)){
            System.out.println("Testcase pass");
        }else{
            System.out.println("Testcase fail");
        }

    }

    @Test
    public void Test_10() {
        Response resp = given().
                parameter("id","2172797").
                parameter("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String reportById = resp.then().
                    contentType(ContentType.JSON).
                    extract().
                    path("weather[0].description");

        System.out.println("Weather description by ID :" + reportById);

        String lon = String.valueOf(resp.then().
                contentType(ContentType.JSON).
                extract().
                path("coord.lon"));
        System.out.println("Longitude: " + lon);

        String lat = String.valueOf(resp.then().
                contentType(ContentType.JSON).
                extract().
                path("coord.lat"));
        System.out.println("Latitude: " + lat);

        Response respBysCoordenate = given().
                parameter("lat",lat).
                parameter("lon",lon).
                parameter("appid","439d4b804bc8187953eb36d2a8c26a02").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        String reportByICoordenate= respBysCoordenate.then().
                contentType(ContentType.JSON).
                extract().
                path("weather[0].description");

        System.out.println("Report by cordenate : " +reportByICoordenate);
    }
}
