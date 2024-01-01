package com.thanh.Bai5_POSTmethod;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPostMethod {

    @Test
    public void testLoginUser() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"thanhtester04\",\n" +
                        "  \"password\": \"Demo@123\"\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);
        System.out.printf(response.path("token"));
    }

    @Test
    public void testRegisterUser() {
        String username = "thanhtester04";
        String firstName = "Thanh";
        String lastName = "Tester";
        String email = username + "@email.com";
        String password = "Demo@123";
        String phone = "0123456789";
        int userStatus = 1;

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\",\n" +
                        "  \"lastName\": \"" + lastName + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"userStatus\": " + userStatus + "\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);

        ResponseBody responseBody = response.getBody();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(responseBody.path("response.username"), username, "The username not match");
        Assert.assertEquals(responseBody.path("response.firstName"), firstName, "The firstname not match");
        Assert.assertEquals(responseBody.path("response.lastName"), lastName, "The lastname not match");
        Assert.assertEquals(responseBody.path("response.email"), email, "The email not match");
//        Assert.assertEquals(responseBody.path("response.password"), username, "The username not match");
        Assert.assertEquals(responseBody.path("response.phone"), phone, "The phone not match");
        Assert.assertEquals(Integer.parseInt(responseBody.path("response.userStatus").toString()), userStatus, "The userStatus not match");
        Assert.assertNotEquals(jsonPath.get("response.id").toString().length(), 0, "Id diffirent 0");
    }
}
