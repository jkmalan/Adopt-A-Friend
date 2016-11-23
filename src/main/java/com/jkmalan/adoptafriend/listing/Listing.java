package com.jkmalan.adoptafriend.listing;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class Listing {

    private final UUID uuid;

    private String title;
    private String sex;
    private String age;
    private String type;
    private String zip;
    private String desc;

    private List<File> photos;
    private List<String> attributes;

    public Listing(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<File> getPhotos() {
        return photos;
    }

    public void setPhotos(List<File> photos) {
        this.photos = photos;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

}
