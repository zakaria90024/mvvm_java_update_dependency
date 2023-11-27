package com.example.mvvm_java_update_dependency.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer_table")
public class Customer {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String strCustomerName;
    private String strPhone;
    private String straddress;

    public Customer(int id, String strCustomerName, String strPhone, String straddress) {
        this.id = id;
        this.strCustomerName = strCustomerName;
        this.strPhone = strPhone;
        this.straddress = straddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrCustomerName() {
        return strCustomerName;
    }

    public void setStrCustomerName(String strCustomerName) {
        this.strCustomerName = strCustomerName;
    }

    public String getStrPhone() {
        return strPhone;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public String getStraddress() {
        return straddress;
    }

    public void setStraddress(String straddress) {
        this.straddress = straddress;
    }
}
