package com.thanh.Bai3_SendRequest_GET;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DemoAddParam {
    @Test
    public void testGetUserByUserName() {
        //Khai báo đối tượng request để thiết lập điều kiện đầu vào
        //Dùng given() chỉ thị sự thiết lập sẵn điều kiện
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .basePath("/user")
                .accept("application/json");

        //Khai báo tham số đầu vào với hàm queryParam
        request.queryParam("username", "anhtester");

        //Khai báo đối tượng response để nhận giá trị trả về từ hàm when() làm gì đó
        //Cụ thể thì chúng ta dùng phương thức GET với hàm get() một endpoint
        Response response = request.when().get();

        //In ra giá trị của response nhận về
        //prettyPrint() là in với nội dung body đã format đẹp mắt
        response.prettyPrint();
        response.then().statusCode(200);
        response.then().statusLine("HTTP/1.1 200 OK");
        response.then().contentType(ContentType.JSON);

        //Đối với body thì cần điền cấu trúc theo xpath từ json
        //Hàm equalTo thuộc thư viện org.hamcrest.Matchers
        response.then().body("response.username", equalTo("anhtester"));
        response.then().body("response.email", containsString("thaian."));
    }
}
