package com.thanh.globals;

import com.thanh.helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String ACCEPT_VALUE = PropertiesHelper.getValue("ACCEPT_VALUE");
}
