package com.thanh.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHelper {
    @SneakyThrows
    public static void updateValueJsonFile(String filePath, String keyName, int value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";


        reader = Files.newBufferedReader(Paths.get(filePath));

        Gson gson = new Gson();
        //Convert Json file to Json Object
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        System.out.println("Original JSON: " + jsonObject);

        //Update value if exist key
        jsonObject.addProperty(keyName, value);

        System.out.println("Modified JSON: " + jsonObject);

        //Store new Json data to new file
        File jsonFile = new File(filePath);
        OutputStream outputStream = new FileOutputStream(jsonFile);
        outputStream.write(gson.toJson(jsonObject).getBytes());
        outputStream.flush();

        //Close reader
        reader.close();
    }

    @SneakyThrows
    public static void updateValueJsonFile(String filePath, String keyName, String value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        reader = Files.newBufferedReader(Paths.get(filePath));

        Gson gson = new Gson();
        //Convert Json file to Json Object
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        System.out.println("Original JSON: " + jsonObject);

        //Update value if exist key
        jsonObject.addProperty(keyName, value);

        System.out.println("Modified JSON: " + jsonObject);

        //Store new Json data to new file
        File jsonFile = new File(filePath);
        OutputStream outputStream = new FileOutputStream(jsonFile);
        outputStream.write(gson.toJson(jsonObject).getBytes());
        outputStream.flush();

        //Close reader
        reader.close();

    }

    @SneakyThrows
    public static void updateValueJsonFile(String filePath, String keyName, Boolean value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        reader = Files.newBufferedReader(Paths.get(filePath));

        Gson gson = new Gson();
        //Convert Json file to Json Object
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        System.out.println("Original JSON: " + jsonObject);

        //Update value if exist key
        jsonObject.addProperty(keyName, value);

        System.out.println("Modified JSON: " + jsonObject);

        //Store new Json data to new file
        File jsonFile = new File(filePath);
        OutputStream outputStream = new FileOutputStream(jsonFile);
        outputStream.write(gson.toJson(jsonObject).getBytes());
        outputStream.flush();

        //Close reader
        reader.close();
    }
}
