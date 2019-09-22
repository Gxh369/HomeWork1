package com.jy.gxh.day3homeworka.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by GXH on 2019/9/20.
 */
@Entity
public  class DatasBean {

    private String title;
    private String thumbnail;
    private String author;
    private int flag;
    @Id
    private long id;
    @Generated(hash = 193447851)
    public DatasBean(String title, String thumbnail, String author, int flag,
            long id) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.author = author;
        this.flag = flag;
        this.id = id;
    }
    @Generated(hash = 128729784)
    public DatasBean() {
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getFlag() {
        return this.flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    

}