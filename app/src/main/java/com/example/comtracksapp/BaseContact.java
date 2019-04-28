package com.example.comtracksapp;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalContact.class, name = "Personal"),
        @JsonSubTypes.Type(value = BusinessContact.class, name = "Business")
})

public class BaseContact implements Comparable<BaseContact>{
    private int id;
    private String type;
    private String name;
    private int phoneNumber;
    private String emailAddress;
    private String address;
    private int photo_id;

    //constructor
    public BaseContact(int id, String type, String name, int phoneNumber, String emailAddress, String address, int photo_id) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.photo_id = photo_id;

    }

    public BaseContact() {}

    //setters and getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString() {
        return name;
    }

    @Override
    public int compareTo(BaseContact o) {
        int compareName = this.name.compareTo(o.name);

        //if the names match, compare id
        if (compareName == 0) {
            return compareName;
        }
        //if names do not match
        else {
            return compareName;
        }
    }
}
