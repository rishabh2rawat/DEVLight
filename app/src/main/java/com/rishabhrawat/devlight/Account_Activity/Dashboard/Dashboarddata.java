package com.rishabhrawat.devlight.Account_Activity.Dashboard;

/**
 * Created by rishabh on 27-Oct-17.
 */

public class Dashboarddata {

    int id;
    String projectImage;
    String ProjectHeader;
    String devname;
    int count;

    public Dashboarddata(int id, String projectImage, String projectHeader, String devname, String authid, int count) {
        this.id = id;
        this.projectImage = projectImage;
        ProjectHeader = projectHeader;
        this.devname = devname;

        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }

    public String getProjectHeader() {
        return ProjectHeader;
    }

    public void setProjectHeader(String projectHeader) {
        ProjectHeader = projectHeader;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Dashboarddata{" +
                "id=" + id +
                ", projectImage='" + projectImage + '\'' +
                ", ProjectHeader='" + ProjectHeader + '\'' +
                ", devname='" + devname + '\'' +
                ", count=" + count +
                '}';
    }
}
