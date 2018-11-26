package com.angelo.blackpearl;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Post {
    @PrimaryKey
    private int id;
    private String desc;
    private String link;
    private String img;
    @ColumnInfo(name = "post_title")
    private String title;


    public Post(){}

    public Post(String title, String desc, String link, String img){
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public String getTitle(){

        return this.title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String mDesc) {
        this.desc = mDesc;
    }

    public String getLink(){

        return this.link;
    }

    public void setLink(String mLink) {
        this.link = mLink;
    }

    public String getImg(){
        return this.img;
    }

    public void setImg(String mImg) {
        this.img = mImg;
    }
}