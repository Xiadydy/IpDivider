package com.gdut.ipdivider.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static String getCurrentDate()
    {
        Date localDate = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd").format(localDate);
    }
}
