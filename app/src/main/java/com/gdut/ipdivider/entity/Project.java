package com.gdut.ipdivider.entity;

import com.gdut.ipdivider.constants.Constant;
import com.gdut.ipdivider.tool.DateUtil;
import java.io.Serializable;
import java.util.List;

public class Project
        implements Serializable
{
    private static final long serialVersionUID = 4356L;
    private String projectName = "";
    private int subNetNum = 0;
    private List<SubnetEntity> subnetEntityList = null;
    private String time = "";

    public Project(String paramString, List<SubnetEntity> paramList)
    {
        this.projectName = paramString;
        this.subnetEntityList = paramList;
        this.subNetNum = paramList.size();
        this.time = DateUtil.getCurrentDate();
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

    public int getSubNetNum()
    {
        return this.subNetNum;
    }

    public List<SubnetEntity> getSubnetEntityList()
    {
        return this.subnetEntityList;
    }

    public String getTime()
    {
        return this.time;
    }
}
