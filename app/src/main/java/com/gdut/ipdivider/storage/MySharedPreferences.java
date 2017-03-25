package com.gdut.ipdivider.storage;

import android.content.*;

public class MySharedPreferences
{
    private SharedPreferences instance;

    public static SharedPreferences getInstance(final TYPE type, final Context context) {
        if (type != TYPE.setting && type == TYPE.record) {
            return context.getSharedPreferences("record", 0);
        }
        new Throwable("Please give the corrective type!");
        return null;
    }

    public enum TYPE
    {
        record("record", 1),
        setting("setting", 0);

        private TYPE(final String s, final int n) {
        }
    }

    public class record
    {
        public static final String NO_MORE_GUIDE = "no_more_guide";
        public static final String USE_TIME = "ues_time";
    }
}
