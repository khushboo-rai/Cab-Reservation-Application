package com.example.taxi_book;

public class Amodel {
    String cab_id;
    String cab_Name;
    String cab_Number;
    String owner_Name;
    String cab_Condition;

    public Amodel() {
    }

    public Amodel(String cab_id, String cab_Name, String cab_Number, String owner_Name, String cab_Condition) {
        this.cab_id = cab_id;
        this.cab_Name = cab_Name;
        this.cab_Number = cab_Number;
        this.owner_Name = owner_Name;
        this.cab_Condition = cab_Condition;
    }

    public String getCab_id() { return cab_id;}

    public void setCab_id(String cab_id) {
        this.cab_id = cab_id;
    }

    public String getCab_Name() {
        return cab_Name;
    }

    public void setCab_Name(String cab_Name) {
        this.cab_Name = cab_Name;
    }

    public String getCab_Number() {
        return cab_Number;
    }

    public void setCab_Number(String cab_Number) {
        this.cab_Number = cab_Number;
    }

    public String getOwner_Name() {
        return owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        this.owner_Name = owner_Name;
    }

    public String getCab_Condition() {
        return cab_Condition;
    }

    public void setCab_Condition(String cab_Condition) {
        this.cab_Condition = cab_Condition;
    }
}
