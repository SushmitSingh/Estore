package com.toon.estore.Model;

public class productinfo {
    String title;
    String Rate;
    String  img;
    String id;
    Long number;
    public productinfo(){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public productinfo(String title, String rate, String img, String id, Long number) {
        this.title = title;
        Rate = rate;
        this.img = img;
        this.id = id;
        this.number = number;
    }



}
