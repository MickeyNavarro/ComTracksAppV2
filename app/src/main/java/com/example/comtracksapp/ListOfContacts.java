package com.example.comtracksapp;

import android.app.Application;

public class ListOfContacts extends Application {

    private AddressBook listOfContacts = new AddressBook();

    public AddressBook getContacts() {
        return listOfContacts;
    }

    public void setContacts(AddressBook listOfContacts) {
        this.listOfContacts = listOfContacts;
    }
}
