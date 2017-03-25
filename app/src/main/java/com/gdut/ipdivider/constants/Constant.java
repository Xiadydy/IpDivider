package com.gdut.ipdivider.constants;

import android.os.Environment;

import java.io.File;

public class Constant
{
    public static final String APP_DIRECTORY = "IpDivider";
    public static final String PROJECT_DIRECTORY_NAME = "Project";
    public static final String PROJECT_TEXT_DIRECTORY_NAME = "Text";

    public static class HandlerWhat
    {
        public static final int SAVE_PROJECT_NAME = 1;
    }

    public static class Storage
    {
        public static final String PROJECT_PATH_ABSOLUTE = File.separator + "IpDivider" + File.separator + "Project";
        public static final String PROJECT_TEXT_PATH_ABSOLUTE = File.separator + "IpDivider" + File.separator + "Text";
        public static String SD_PATH = setSDPath();
    }

    public static String setSDPath(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return Environment.getExternalStorageDirectory().getAbsolutePath();

        }
        return null;
    }
}
