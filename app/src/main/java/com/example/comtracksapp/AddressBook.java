package com.example.comtracksapp;

import android.content.Context;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    List<BaseContact> listOfContacts;

    public AddressBook(List<BaseContact> listOfContacts) {
        this.listOfContacts = new ArrayList<>();
    }

    public AddressBook() {
        //this.listOfContacts = new ArrayList<>();

        /*//create 3 instances of contacts, locations, and photos that belong to a single contact
        Photo p1 = new Photo(1, "photo1.jpg", "February 17, 2019", "None");
        PersonalContact personal = new PersonalContact(1, "Personal", "Jane Doe", 1234567890, "jd@gmail.com", "1111 Fake Addy", 1, "January 1, 1999", "Friend", "None");

        Photo p2 = new Photo(2, "photo1.jpg", "February 17, 2019", "None");
        BusinessContact business =  new BusinessContact(2, "Business", "Dutch Bros", 199999992, "dbaz@gmail.com", "1111 Fake Addy", 2, "5 AM - 11 PM", "www.dutchbros.com", "Coffee company");

        Photo p3 = new Photo(3, "photo1.jpg", "February 17, 2019", "None");
        BusinessContact business2 =  new BusinessContact(3, "Business", "Starbucks", 199999992, "starbucks@gmail.com","1111 Fake Addy", 3, "5 AM - 8 PM", "www.starbucks.com", "Coffee company");

        listOfContacts.add(personal);
        listOfContacts.add(business);
        listOfContacts.add(business2);
        */

    }

    public List<BaseContact> getAddressBook() {
        return listOfContacts;
    }

    public void setAddressBook(List<BaseContact> listOfContacts) {
        this.listOfContacts = listOfContacts;
    }

    public void saveContacts(Context context) {
        DataService ds = new DataService(context);
        ds.writeAllData(this);
    }

    @JsonIgnore
    public void loadContacts(Context context) {
        DataService ds = new DataService(context);
        listOfContacts = ds.readAllData().getAddressBook();
    }
}
