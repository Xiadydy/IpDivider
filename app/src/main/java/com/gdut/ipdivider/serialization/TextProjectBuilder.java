package com.gdut.ipdivider.serialization;

import com.gdut.ipdivider.constants.*;
import java.io.*;
import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.tool.FileUtil;
import com.gdut.ipdivider.tool.StatService;

import java.util.*;

public class TextProjectBuilder extends BaseBuilder<Project, String>
{
    private String TextProjectPath;

    public TextProjectBuilder() {
        this.TextProjectPath = Constant.Storage.SD_PATH + Constant.Storage.PROJECT_TEXT_PATH_ABSOLUTE;
        System.out.println(TextProjectPath);
    }

    @Override
    public Project build(final String s) {
        try {
            return (Project)new ObjectInputStream(new FileInputStream(s)).readObject();
        }
        catch (FileNotFoundException ex4) {
            ex4.printStackTrace();
            return null;
        }
        catch (StreamCorruptedException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException ex3) {
            ex3.printStackTrace();
            return null;
        }
    }

    @Override
    public void deconstruct(final Project project) {
        final File file = new File(this.TextProjectPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file2 = new File(String.valueOf(file.getAbsolutePath()) + File.separator + project.getProjectName() + ".xls");
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                System.err.println("创建文件失败");
                e.printStackTrace();
            }
        }
        System.out.println(file2.getAbsoluteFile());
        List<SubNetInfomationBean> result = project.getResult();
        List<Map<String, Object>> data = StatService.object2Map(result);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file2);
            FileUtil.getInstance().exportExcel(out,data,new String[]{"子网号", "子网别名", "所需IP数量",
                    "网段地址", "地址范围", "广播地址", "子网掩码", "推荐默认网关", "已用IP数量", "未用IP数量", "剩余地址池"
            }, new String[]{"subNetId", "subMaskName", "needIpCount", "subNetAdress", "subNetScope",
                    "broadCastAdress", "mask","defaultGetWay", "alreadyUseConut","notUseCount", "restAdressPool"},null,null,result);
        } catch (FileNotFoundException e) {
            System.err.println("没有该文件");
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void exportProject(Project project,String name){
        final File file = new File(this.TextProjectPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file2 = new File(String.valueOf(file.getAbsolutePath()) + File.separator + name + ".xls");
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                System.err.println("创建文件失败");
                e.printStackTrace();
            }
        }
        System.out.println(file2.getAbsoluteFile());
        List<SubNetInfomationBean> result = project.getResult();
        List<Map<String, Object>> data = StatService.object2Map(result);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file2);
            FileUtil.getInstance().exportExcel(out,data,new String[]{"子网号", "子网别名", "所需IP数量",
                    "网段地址", "地址范围", "广播地址", "子网掩码", "推荐默认网关", "已用IP数量", "未用IP数量", "剩余地址池"
            }, new String[]{"subNetId", "subMaskName", "needIpCount", "subNetAdress", "subNetScope",
                    "broadCastAdress", "mask","defaultGetWay", "alreadyUseConut","notUseCount", "restAdressPool"},
                    null,null, result);
        } catch (FileNotFoundException e) {
            System.err.println("没有该文件");
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
