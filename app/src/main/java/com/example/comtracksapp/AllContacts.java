package com.example.comtracksapp;

 import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class AllContacts extends AppCompatActivity{
    Button btn_back, btn_save;

    ListView lv_allContacts;

    BaseContactAdapter adapter;

    AddressBook listOfContacts;

    Spinner spinner_add;

    SearchView searchV_onAllContacts;
    AddressBook searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        btn_back = findViewById(R.id.btn_back);
        btn_save = findViewById(R.id.btn_save);
        lv_allContacts = findViewById(R.id.lv_allContacts);

        searchV_onAllContacts = findViewById(R.id.searchV_onAllContacts);

        spinner_add = findViewById(R.id.spinner_add);


        //load the contacts upon creation
        listOfContacts = ((ListOfContacts) this.getApplication()).getContacts();
        listOfContacts.loadContacts(getApplicationContext());
        adapter = new BaseContactAdapter(AllContacts.this, listOfContacts);
        lv_allContacts.setAdapter(adapter);

        // get the array of spinner items and put it into the dropdown manu
        ArrayAdapter<CharSequence> arrAdapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_add.setAdapter(arrAdapter);

        spinner_add.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //check what type of contact they want to add
                if (spinner_add.getItemAtPosition(position).toString().matches("Personal")) {
                    Intent add = new Intent(view.getContext(), AddPersonalContact.class );
                    startActivity(add);
                }
                else if (spinner_add.getItemAtPosition(position).toString().matches("Business")) {
                    Intent add = new Intent(view.getContext(), AddBusinessContact.class );
                    startActivity(add);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing will happen
            }

        });

        //listen for incoming messages
        Bundle incomingMessage = getIntent().getExtras();

        if (incomingMessage != null) {
            //capture incoming data
            String name = incomingMessage.getString("name");
            int phone = incomingMessage.getInt("phone");
            String email = incomingMessage.getString("email");
            String address = incomingMessage.getString("addy");
            String type = incomingMessage.getString("type");

            //used to check if the contact was editted

            int position = -1;
            if (incomingMessage.get("pos") != null) {

                position = incomingMessage.getInt("pos");
            }
            int id = incomingMessage.getInt("id");

            //capture personal data
            String nn = incomingMessage.getString("nn");
            String dob = incomingMessage.getString("dob");
            String rel = incomingMessage.getString("rel");

            //capture business data
            String bh = incomingMessage.getString("bh");
            String web = incomingMessage.getString("web");
            String des = incomingMessage.getString("des");


            //create new contact based on data
            if (type.matches("Personal")) {
                PersonalContact p = new PersonalContact(-1, type, name, phone, email, address, 0, dob, rel, nn);
                //add person to the list and update adapter
                if (position > -1 && incomingMessage.get("pos") != null) {
                    listOfContacts.getAddressBook().remove(position);
                }

                listOfContacts.getAddressBook().add(p);
            } else if (type.matches("Business")) {
                BusinessContact b = new BusinessContact(-1, type, name, phone, email, address, 0 , bh, web, des);
                //add person to the list and update adapter
                if (position > -1) {
                    listOfContacts.getAddressBook().remove(position);
                }

                listOfContacts.getAddressBook().add(b);
            }

            //update the list view
            adapter.notifyDataSetChanged();

            listOfContacts.saveContacts(getApplicationContext());
        }


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(v.getContext(), Home.class );
                startActivity(back);
            }
        });

        lv_allContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewContact(position);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfContacts.saveContacts(v.getContext());

                Toast.makeText(AllContacts.this, "Your contacts have been saved!",
                        Toast.LENGTH_SHORT).show();
            }
        });


        searchV_onAllContacts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String text = query;
                search(text);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                search(text);
                return true;
            }
        });

    }

    public void viewContact(int position) {
        //get the contents of person at position
        BaseContact b = listOfContacts.getAddressBook().get(position);

        //check the type of contact
        if (b.getType().matches("Personal")) {

            //set to a personal contact
            PersonalContact pc = (PersonalContact) listOfContacts.getAddressBook().get(position);

            Intent i = new Intent(getApplicationContext(), ContactProfile.class);

            i.putExtra("pos", position);
            i.putExtra("name", pc.getName());
            i.putExtra("type", pc.getType());
            i.putExtra("phone", pc.getPhoneNumber());
            i.putExtra("email", pc.getEmailAddress());
            i.putExtra("addy", pc.getAddress());
            i.putExtra("nn", pc.getNickname());
            i.putExtra("dob", pc.getDateOfBirth());
            i.putExtra("rel", pc.getRelation());
            i.putExtra("type", pc.getType());
            i.putExtra("id", pc.getId());

            startActivity(i);


        } else if (b.getType().matches("Business")) {

            //set to a personal contact
            BusinessContact bc = (BusinessContact) listOfContacts.getAddressBook().get(position);

            Intent i = new Intent(getApplicationContext(), ContactProfile.class);

            i.putExtra("pos", position);
            i.putExtra("name", bc.getName());
            i.putExtra("type", bc.getType());
            i.putExtra("phone", bc.getPhoneNumber());
            i.putExtra("email", bc.getEmailAddress());
            i.putExtra("addy", bc.getAddress());
            i.putExtra("bh", bc.getBusinessHours());
            i.putExtra("web", bc.getWebsite());
            i.putExtra("des", bc.getDescription());
            i.putExtra("type", bc.getType());
            i.putExtra("id", bc.getId());

            startActivity(i);

        }

    }

    // search class
    public void search(String charText) {

        searchResults = new AddressBook(new ArrayList<BaseContact>());
        //searchResults.setAddressBook(listOfContacts.getAddressBook());
        charText = charText.toLowerCase(Locale.getDefault());

        //check if there is no search queries
        if (charText.length() == 0) {
            adapter = new BaseContactAdapter(AllContacts.this, listOfContacts);
            lv_allContacts.setAdapter(adapter);

        } else {
            //check the address book for contacts that match the search queries
            for (BaseContact b : listOfContacts.getAddressBook()) {
                if (b.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    searchResults.getAddressBook().add(b);
                }
            }
            adapter = new BaseContactAdapter(AllContacts.this, searchResults);
            lv_allContacts.setAdapter(adapter);

        }
        adapter.notifyDataSetChanged();
    }
}
