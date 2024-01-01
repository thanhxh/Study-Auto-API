package com.thanh.model.data;

import com.thanh.globals.ConfigsGlobal;
import com.thanh.model.LoginPOJO;

public class LoginPOJO_Builder {
    public static LoginPOJO getLoginData() {
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }
}
