package com.gdut.ipdivider.entity;

import com.gdut.ipdivider.tool.DateUtil;
import java.io.Serializable;
import java.util.List;

public class Project
        implements Serializable
{
    private static final long serialVersionUID = 4356L;
    private String projectName = "";
    private String time = "";
    //lvdi start
    private List<SubNetInfomationBean> result;

    public Project(String paramString, List<SubNetInfomationBean> result)
    {
        this.projectName = paramString;
        this.time = DateUtil.getCurrentDate();
        this.result = result;
    }


    public String getProjectName()
    {
        return this.projectName;
    }

    public String getTime()
    {
        return this.time;
    }

    public List<SubNetInfomationBean> getResult(){
        return this.result;
    }
}
