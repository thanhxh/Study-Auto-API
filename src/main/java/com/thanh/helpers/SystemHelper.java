package com.thanh.helpers;

import java.io.File;

public class SystemHelper {
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }
}
