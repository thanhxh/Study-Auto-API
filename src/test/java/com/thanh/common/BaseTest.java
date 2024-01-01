package com.thanh.common;

import com.google.gson.Gson;
import com.thanh.globals.ConfigsGlobal;
import com.thanh.globals.TokenGlobal;
import com.thanh.helpers.PropertiesHelper;
import com.thanh.model.LoginPOJO;
import com.thanh.model.data.LoginPOJO_Builder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeSuite
    public void setupSuite() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
//        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO loginPOJO = LoginPOJO_Builder.getLoginData();

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_VALUE)
                .contentType(ContentType.JSON)
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post(BasePathCommon.LOGIN);
//        response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }
}
