package tech.ryanfehr.androiddevweek4assignmentapplication3;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryan on 9/14/2017.
 */

class FileData implements Serializable{
    private String name;
    private String content;
    private Date modifiedDate;
    private double size;

    public FileData(String name, String content, Date modifiedDate, double size) {
        this.name = name;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
