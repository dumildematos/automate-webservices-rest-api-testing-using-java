package org.example;

import APITesting.com.org.classes.Posts;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

public class JsonServerRequest {

    // GET Method
    //@Test
    public void Test_01(){
        Response resp = given().
                when().
                get("http://localhost:3000/posts");

        System.out.println("Posts List : " + resp.asString());
    }

    // POST Method
    //@Test
    public void Test_02() {
            given()
                .body("{\"id\":\"2\","
                    + "\"title\": \"Test\","
                    + "\"author\": \"Hannibal\"}").
                when().
                contentType(ContentType.JSON).
                post("http://localhost:3000/posts");
    }

    //@Test
    public void Test_03(){
        Posts posts = new Posts();
        posts.setId(3);
        posts.setTitle("The start");
        posts.setAuthor("Someone");

        Response resp = given().
                    when().
                    contentType(ContentType.JSON).
                    body(posts).
                    post("http://localhost:3000/posts");

        System.out.println(resp.asString());
    }

    // GET by ID
    //@Test
    public void Test_04(){
        Response resp = given().
                    when().
                    get("http://localhost:3000/posts/3");

        System.out.println(resp.asString());
    }

    //PUT
    //@Test
    public void Test_05(){
        Posts post = new Posts();
        post.setId(3);
        post.setTitle("Darker Moon");
        post.setAuthor("Antony Mc.gray");

        Response resp = given().
                when().
                contentType(ContentType.JSON).
                body(post).
                put("http://localhost:3000/posts/3");

        System.out.println("Response " + resp.asString());
    }

    // PATCH
    //@Test
    public void Test_06(){
        Response resp = given().
            body("{\"title\":\"patch\"}").
                when().
                contentType(ContentType.JSON).
                patch("http://localhost:3000/posts/3");

        System.out.println("Patch response : " + resp.asString());
    }

    // DELETE
    @Test
    public void Test_07(){
        Response resp = given().
                when().
                delete("http://localhost:3000/posts/3");

        System.out.println(resp.asString());
    }


}