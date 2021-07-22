package com.toon.estore.Model;

public class SignUser {
public String name,email,phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SignUser(){

    }
    public SignUser(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


}
