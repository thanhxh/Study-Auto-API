package com.thanh.Bai10_ReadPropertiesFile;

import com.google.gson.Gson;
import com.thanh.common.BasePathCommon;
import com.thanh.common.BaseTest;
import com.thanh.globals.ConfigsGlobal;
import com.thanh.globals.TokenGlobal;
import com.thanh.model.RegisterUserPOJO_Lombok;
import com.thanh.model.data.UserPOJO_Lombok_Builder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoBaseTest extends BaseTest {
    @Test
    public void testUpdateUser_PATCH() {

        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");

        RegisterUserPOJO_Lombok registerUserPOJO_lombok = new RegisterUserPOJO_Lombok();
        registerUserPOJO_lombok.setFirstName(faker.name().firstName());
        registerUserPOJO_lombok.setLastName(faker.name().lastName());
        registerUserPOJO_lombok.setEmail(faker.internet().emailAddress());
        registerUserPOJO_lombok.setPhone(phoneNumber);
        registerUserPOJO_lombok.setUserStatus(0);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_VALUE)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch(BasePathCommon.USER_ID_25);
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

    @Test
    public void testUpdateUser_PATCH_Builder() {

        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserData();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_VALUE)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch(BasePathCommon.USER_ID_25);
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
