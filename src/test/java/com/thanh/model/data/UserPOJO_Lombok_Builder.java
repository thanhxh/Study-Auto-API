package com.thanh.model.data;

import com.thanh.model.RegisterUserPOJO_Lombok;
import net.datafaker.Faker;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {
    public static RegisterUserPOJO_Lombok getUserData() {
        Faker faker = new Faker(new Locale("vi"));

        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");
        System.out.println(phoneNumber);
        //Chuẩn bị data cho edit user
        return RegisterUserPOJO_Lombok.builder()
                .username(faker.color().name())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(phoneNumber)
                .userStatus(0)
                .build();
    }
}
