package com.example.healthylife;

import java.io.Serializable;

public class UserData implements Serializable {
    private String Identification;
    private String password;
    private String mailAddress;
    private byte gender;

    public UserData(){
        super();
    }

    public UserData(String id,String pw,String mailAd,byte gender){
        Identification=id;
        password=pw;
        mailAddress=mailAd;
        this.gender=gender;
    }

    public String getIdentification() {
        return Identification;
    }

    public String getPassword() {
        return password;
    }

    public byte getGender() {
        return gender;
    }

    public void resetPassword(String new_password){
        password=new_password;
    }
}
