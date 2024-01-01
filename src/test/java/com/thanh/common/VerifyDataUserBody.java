package com.thanh.common;

import com.thanh.model.RegisterUserPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataUserBody {
    public static void verifyDataBodyUser(Response response, RegisterUserPOJO registerUserPOJO) {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.username"), registerUserPOJO.getUsername(), "The username not match.");
        Assert.assertEquals(jsonPath.get("response.firstName"), registerUserPOJO.getFirstName(), "The firstName not match.");
        Assert.assertEquals(jsonPath.get("response.lastName"), registerUserPOJO.getLastName(), "The lastName not match.");
        Assert.assertEquals(jsonPath.get("response.email"), registerUserPOJO.getEmail(), "The email not match.");
        Assert.assertEquals(jsonPath.get("response.phone"), registerUserPOJO.getPhone(), "The phone not match.");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.userStatus").toString()), registerUserPOJO.getUserStatus(), "The userStatus not match.");
        Assert.assertNotEquals(jsonPath.get("response.id").toString().length(), 0, "Id diffirent 0");
    }

}
