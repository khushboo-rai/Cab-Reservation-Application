package com.example.taxi_book;

public class DriverDetails {

    String Email;
    String Name;
    String Phone_Number;
    String Address;

    public DriverDetails() {
    }

    public DriverDetails(String email, String name, String phone_Number, String address) {
        this.Email = email;
        this.Name = name;
        this.Phone_Number = phone_Number;
        this.Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
