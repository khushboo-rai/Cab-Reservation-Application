package com.example.taxi_book;

public class RBookingDetails {
    String Name,Email,Phone_Number,Passenger_Number;

    public RBookingDetails() {
    }

    public RBookingDetails(String name, String email, String phone_Number, String passenger_Number) {
        Name = name;
        Email = email;
        Phone_Number = phone_Number;
        Passenger_Number = passenger_Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getPassenger_Number() {
        return Passenger_Number;
    }

    public void setPassenger_Number(String passenger_Number) {
        Passenger_Number = passenger_Number;
    }
}
