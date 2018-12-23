package com.example.ett15084.harkkatyo;

public class MyInfoHandler {

    // This class keeps track of the person's info in they choose to write them in the MyInfoFragment

    private static MyInfoHandler myInfoHandler = new MyInfoHandler();

    String defaultName = "";
    String defaultEmail = "";
    String defaultPhoneNumber = "";

    public String getDefaultName() {
        return defaultName;
    }

    public String getDefaultEmail() {
        return defaultEmail;
    }

    public String getDefaultPhoneNumber() {
        return defaultPhoneNumber;
    }

    public void setMyInfoHandler(String n, String e, String ph){
        defaultPhoneNumber = ph;
        defaultEmail = e;
        defaultName = n;
    }

    public static MyInfoHandler getInstance(){
        return myInfoHandler;
    }
}
