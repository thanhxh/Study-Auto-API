package com.thanh.Bai13_ThucHanh;

import com.google.gson.Gson;
import com.thanh.common.BaseTest;
import com.thanh.globals.ConfigsGlobal;
import com.thanh.globals.TokenGlobal;
import com.thanh.helpers.JsonHelper;
import com.thanh.model.BookPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BookTest extends BaseTest {

    int CATEGORY_ID;
    int BOOK_ID;

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");

        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));

        JsonHelper.updateValueJsonFile(dataFile, "name", faker.job().title());

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");

        response.prettyPrint();
        response.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
        System.out.println(CATEGORY_ID);

    }

    @Test(priority = 2)
    public void testAddNewBook() {

        Faker faker = new Faker(new Locale("vi"));

        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2015-12-30");
        bookPOJO.setStatus(true);

//        ArrayList<Integer> imagesID = new ArrayList<>();
//        imagesID.add(1);
//        imagesID.add(2);

        //bookPOJO.setImage_ids(imagesID);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(2, 18, 19)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.post("/book");
        response.prettyPrint();

        response.then().statusCode(200);
        BOOK_ID = response.path("response.id");
        System.out.println("BOOK_ID: " + BOOK_ID);

    }

    @Test(priority = 3)
    public void testPutNewBook() {

        Faker faker = new Faker(new Locale("vi"));

        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(CATEGORY_ID);
        bookPOJO.setPrice(150000);
        bookPOJO.setRelease_date("2015-12-30");
        bookPOJO.setStatus(true);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(21, 18, 19)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.put("/book/" + BOOK_ID);
        response.prettyPrint();

        response.then().statusCode(200);
    }

    @Test(priority = 4)
    public void testDeleteNewBook() {

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response response = request.delete("/book/" + BOOK_ID);
        response.prettyPrint();

        response.then().statusCode(200);
    }
}
