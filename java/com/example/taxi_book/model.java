package com.example.taxi_book;

public class model {
    String cab_Number,cab_Name,driver_Name,driver_Number,cab_Condition;

    public model() {

    }

    public model(String cab_Number, String cab_Name, String driver_Name, String driver_Number, String cab_Condition) {
        this.cab_Number = cab_Number;
        this.cab_Name = cab_Name;
        this.driver_Name = driver_Name;
        this.driver_Number = driver_Number;
        this.cab_Condition = cab_Condition;
    }

    public String getCab_Number() {
        return cab_Number;
    }

    public void setCab_Number(String cab_Number) {
        this.cab_Number = cab_Number;
    }

    public String getCab_Name() {
        return cab_Name;
    }

    public void setCab_Name(String cab_Name) {
        this.cab_Name = cab_Name;
    }

    public String getDriver_Name() {
        return driver_Name;
    }

    public void setDriver_Name(String driver_Name) {
        this.driver_Name = driver_Name;
    }

    public String getDriver_Number() {
        return driver_Number;
    }

    public void setDriver_Number(String driver_Number) {
        this.driver_Number = driver_Number;
    }

    public String getCab_Condition() {
        return cab_Condition;
    }

    public void setCab_Condition(String cab_Condition) {
        this.cab_Condition = cab_Condition;
    }
}
