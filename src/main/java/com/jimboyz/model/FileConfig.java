package com.jimboyz.model;

import java.io.File;

public class FileConfig {

    public static String getFileConfig() {
        return System.getProperty("user.dir") + File.separator +  "client.conf";
    }
}
