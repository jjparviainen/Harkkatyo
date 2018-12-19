package com.example.ett15084.harkkatyo;



// Tässä luokassa luodaan uusi Booking-olio eli varaus joka sisältää kaikki tarvittavat tiedot

public class Bookings {

    String name = "";
    String email = "";
    String phoneNumber = "";
    String timeBooked = "";
    String date;
    String sport;

    public Bookings(String n, String e, String pn, String tb, String d, String s){
        name = n;
        email = e;
        phoneNumber = pn;
        timeBooked = tb;
        date = d;
        sport = s;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public String  getDate() {
        return date;
    }

    public String getSport() {
        return sport;
    }
}
