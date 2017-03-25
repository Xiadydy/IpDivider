package com.gdut.ipdivider.serialization;

import com.gdut.ipdivider.constants.*;
import java.io.*;
import com.gdut.ipdivider.entity.*;
import java.util.*;

public class TextProjectBuilder extends BaseBuilder<Project, String>
{
    private static final String WIN_BREAK = "\r\n";
    private String TextProjectPath;

    public TextProjectBuilder() {
        this.TextProjectPath = String.valueOf(Constant.Storage.SD_PATH) + Constant.Storage.PROJECT_TEXT_PATH_ABSOLUTE;
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
        final File file2 = new File(String.valueOf(file.getPath()) + File.separator + project.getProjectName() + ".txt");
        try {
            System.out.println(file2.toString());
            final PrintWriter printWriter = new PrintWriter(new FileOutputStream(file2));
            for (final SubnetEntity subnetEntity : project.getSubnetEntityList()) {
                printWriter.print("\u5b50\u7f51\uff1a" + subnetEntity.getName() + "\r\n" + "\u7f51\u6bb5\uff1a" + subnetEntity.getSegment() + "\r\n" + "\u8303\u56f4\uff1a" + subnetEntity.getIpRange() + "\r\n" + "\u63a9\u7801\uff1a" + subnetEntity.getMaskNum() + "\r\n" + "\u5df2\u7528\uff1a" + subnetEntity.getIpCount() + "    " + "\u672a\u7528\uff1a" + subnetEntity.getNoUseIPCount() + "\r\n" + "\r\n");
            }
            printWriter.close();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
}
