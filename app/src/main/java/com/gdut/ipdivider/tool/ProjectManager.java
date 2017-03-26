package com.gdut.ipdivider.tool;

import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.constants.*;
import java.io.*;
import java.util.*;
import com.gdut.ipdivider.serialization.*;

public class ProjectManager
{
    private static List<Project> allProjectList;
    private static final String path;

    static {
        path = Constant.Storage.SD_PATH + Constant.Storage.PROJECT_PATH_ABSOLUTE;
        ProjectManager.allProjectList = null;
    }

    public static void deleteProject(final String s) {
        new File(String.valueOf(ProjectManager.path) + File.separator + s + ".project").delete();
    }

    public static List<Project> getAllProjectFromDisk() {
        if (ProjectManager.allProjectList == null) {
            ProjectManager.allProjectList = new ArrayList<Project>();
        }
        ProjectManager.allProjectList.removeAll(ProjectManager.allProjectList);
        final File file = new File(Constant.Storage.SD_PATH + Constant.Storage.PROJECT_PATH_ABSOLUTE);
        if(!file.exists()){
            return Collections.emptyList();
        }
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file2 = listFiles[i];
                if (file2.getName().contains(".project")) {
                    ProjectManager.allProjectList.add(new ProjectBuilder().build(file2.getAbsolutePath()));
                }
            }
        }
        return ProjectManager.allProjectList;
    }
}
