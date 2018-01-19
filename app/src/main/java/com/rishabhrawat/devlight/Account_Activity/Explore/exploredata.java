package com.rishabhrawat.devlight.Account_Activity.Explore;

import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.id;

/**
 * Created by rishabh on 23-Oct-17.
 */

public class exploredata {

    int id;
    String projectImage;
    String projectHeader;
    String projectText;
    String devname;
    String devemail;

    public exploredata(int id, String projectImage, String projectHeader, String projectText, String devname, String devemail) {
        this.id = id;
        this.projectImage = projectImage;
        this.projectHeader = projectHeader;
        this.projectText = projectText;
        this.devname = devname;
        this.devemail = devemail;
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
        return projectHeader;
    }

    public void setProjectHeader(String projectHeader) {
        this.projectHeader = projectHeader;
    }

    public String getProjectText() {
        return projectText;
    }

    public void setProjectText(String projectText) {
        this.projectText = projectText;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getDevemail() {
        return devemail;
    }

    public void setDevemail(String devemail) {
        this.devemail = devemail;
    }

    @Override
    public String toString() {
        return "exploredata{" +
                "id=" + id +
                ", projectImage='" + projectImage + '\'' +
                ", projectHeader='" + projectHeader + '\'' +
                ", projectText='" + projectText + '\'' +
                ", devname='" + devname + '\'' +
                ", devemail='" + devemail + '\'' +
                '}';
    }
}
