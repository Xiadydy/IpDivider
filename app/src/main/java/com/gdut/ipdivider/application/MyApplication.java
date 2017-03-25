package com.gdut.ipdivider.application;

        import android.app.Activity;
        import android.app.Application;
        import android.content.SharedPreferences;
        import android.os.Environment;
        import com.gdut.ipdivider.storage.MySharedPreferences;
        import com.gdut.ipdivider.storage.MySharedPreferences.TYPE;
        import java.io.File;
        import java.io.PrintStream;
        import java.util.LinkedList;
        import java.util.List;
/**
 * <p>Title: 9game</p>
 * <p>
 * <p>Description: </p>
 * //TODO Description
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>
 * <p>Company: 9game.cn</p>
 *
 * @author xinxiao.cxx@alibaba-inc.com
 * @version 1.0
 * @Date: 2017/3/15 12:29
 */


public class MyApplication
        extends Application
{
    private File SdCardPath;
    private List<Activity> activityList = new LinkedList();
    public Boolean isShowed = Boolean.valueOf(false);

    public boolean isWant2Guide()
    {
        boolean bool2 = false;
        Object localObject = MySharedPreferences.getInstance(MySharedPreferences.TYPE.record, getApplicationContext());
        ((SharedPreferences)localObject).edit().putInt("ues_time", ((SharedPreferences)localObject).getInt("ues_time", 0) + 1).commit();
        int i = ((SharedPreferences)localObject).getInt("ues_time", 0);
        boolean bool3 = ((SharedPreferences)localObject).getBoolean("no_more_guide", false);
        System.out.println("time = " + i + "\n noMoreGuide = " + bool3 + "isShowed = " + this.isShowed);
        if (((1 == i) || (!bool3)) && (!this.isShowed.booleanValue()))
        {
            localObject = System.out;
            StringBuilder localStringBuilder = new StringBuilder("show??= ");
            boolean bool1;
            if (1 != i)
            {
                bool1 = bool2;
                if (bool3) {}
            }
            else
            {
                bool1 = bool2;
                if (!this.isShowed.booleanValue()) {
                    bool1 = true;
                }
            }
            ((PrintStream)localObject).println(bool1);
            return true;
        }
        return false;
    }

    public void onCreate()
    {
        super.onCreate();
        isWant2Guide();
        this.SdCardPath = Environment.getExternalStorageDirectory();
        com.gdut.ipdivider.constants.Constant.Storage.SD_PATH = this.SdCardPath.getAbsolutePath();
    }
}

