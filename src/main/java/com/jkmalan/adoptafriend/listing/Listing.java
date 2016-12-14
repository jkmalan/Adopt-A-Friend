package com.jkmalan.adoptafriend.listing;

import java.io.File;

public class Listing {

    private final int lid;
    private final int owner;

    private String title;
    private String zip;
    private String type;
    private String sex;
    private int age;
    private String desc;
    private File photo;

    public Listing(int lid, int owner) {
        this.lid = lid;
        this.owner = owner;
    }

    public int getListingID() {
        return lid;
    }

    public int getOwnerID() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZip() {
        return zip;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

}
