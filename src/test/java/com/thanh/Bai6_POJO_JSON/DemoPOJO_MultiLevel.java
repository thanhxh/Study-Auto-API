package com.thanh.Bai6_POJO_JSON;

import com.google.gson.Gson;
import com.thanh.model.BookingBody;
import com.thanh.model.BookingDates;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DemoPOJO_MultiLevel {
    @Test
    public void testCreateBooking() {
        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Content-Type", "application/json")
                .header("Accept", "application/json");
        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        bookingBody.setFirstname("THANH");
        bookingBody.setLastname("TESTER");
        bookingBody.setTotalprice(1111);
        bookingBody.setDepositpaid(true);
        bookingBody.setAdditionalneeds("Technology");

        bookingDates.setCheckin("2023-12-15");
        bookingDates.setCheckout("2023-12-30");
        bookingBody.setBookingDates(bookingDates);
        Gson gson = new Gson();

        //Convert POJO to Json
        request.body(gson.toJson(bookingBody));

        Response response = request.when().post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
        String token = response.getBody().path("token");
        System.out.println(token);
    }

    @Test
    public void testLoginUser() {
        String filePath = "src/test/resources/login.json";
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));

        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        String token = response.getBody().path("token");
        System.out.println(token);
    }

}
