package tech.ryanfehr.androiddevweek5assignment;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryan on 9/21/2017.
 */

public class Task implements Serializable{
    private String title;
    private String content;
    private Date finishByDate;

    public Task(String title, String content, Date finishByDate) {
        this.title = title;
        this.content = content;
        this.finishByDate = finishByDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFinishByDate() {
        return finishByDate;
    }

    public void setFinishByDate(Date finishByDate) {
        this.finishByDate = finishByDate;
    }
}
