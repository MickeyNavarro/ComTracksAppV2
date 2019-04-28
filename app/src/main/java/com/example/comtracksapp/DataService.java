package com.example.comtracksapp;

import android.content.Context;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataService {

    Context context;

    ObjectMapper om = new ObjectMapper();

    public DataService(Context context){this.context = context; }

    public void writeAllData(AddressBook listOfContacts) {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, "AddressBook.txt");
        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, listOfContacts);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public AddressBook readAllData() {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, "AddressBook.txt");
        AddressBook addressBook = new AddressBook();

        try{
            addressBook = om.readValue(file, AddressBook.class);
        } catch (IOException e){
            e.printStackTrace();
        }

        return addressBook;
    }
}
