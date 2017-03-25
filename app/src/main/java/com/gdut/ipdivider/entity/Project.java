package com.gdut.ipdivider.entity;

import com.gdut.ipdivider.constants.Constant;
import com.gdut.ipdivider.tool.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project
        implements Serializable
{
    private static final long serialVersionUID = 4356L;
    private String projectName = "";
    private int subNetNum = 0;
    private List<SubnetEntity> subnetEntityList = null;
    private String time = "";
    //lvdi start
    private List<SubNetInfomationBean> result;

    public Project(String paramString, List<SubNetInfomationBean> result)
    {
        this.projectName = paramString;
        this.subNetNum = result.size();
        this.time = DateUtil.getCurrentDate();
        this.result = result;
    }

    public String getLocalPath()
    {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(Constant.Storage.SD_PATH + Constant.Storage.PROJECT_PATH_ABSOLUTE + getProjectName());
        return localStringBuffer.toString();
    }

    public String getProjectName()
    {
        return this.projectName;
    }


    public List<SubnetEntity> getSubnetEntityList()
    {
        return this.subnetEntityList;
    }

    public String getTime()
    {
        return this.time;
    }

    public List<SubNetInfomationBean> getResult(){
        return this.result;
    }
}
