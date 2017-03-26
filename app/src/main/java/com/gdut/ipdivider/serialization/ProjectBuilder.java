package com.gdut.ipdivider.serialization;

import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.constants.*;
import java.io.*;

public class ProjectBuilder extends BaseBuilder<Project, String>
{
    private String Projectpath;

    public ProjectBuilder() {
        this.Projectpath = Constant.Storage.SD_PATH + Constant.Storage.PROJECT_PATH_ABSOLUTE;
    }

    @Override
    public Project build(final String s) {
        try {
            return (Project)new ObjectInputStream(new FileInputStream(new File(s))).readObject();
        }
        catch (FileNotFoundException ex4) {
            System.err.println("文件无法找到");
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
        final File file = new File(this.Projectpath);
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file2 = new File(String.valueOf(file.getPath()) + File.separator + project.getProjectName() + ".project");
        try {
            System.out.println(file2.toString());
            new ObjectOutputStream(new FileOutputStream(file2)).writeObject(project);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
}
