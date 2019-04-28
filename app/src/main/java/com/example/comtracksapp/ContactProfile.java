package com.example.comtracksapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactProfile extends AppCompatActivity {

    Button btn_back, btn_edit, btn_delete, btn_call, btn_email, btn_text;

    TextView tv_name, tv_phone, tv_email, tv_addy, tv_misc1, tv_misc2, tv_misc3, tv_MISC1, tv_MISC2, tv_MISC3, tv_delete;

    AddressBook listOfContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        listOfContacts = ((ListOfContacts) this.getApplication()).getContacts();

        btn_back = findViewById(R.id.btn_back);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        btn_call = findViewById(R.id.btn_call);
        btn_email = findViewById(R.id.btn_email);
        btn_text = findViewById(R.id.btn_text);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_email = findViewById(R.id.tv_email);
        tv_addy = findViewById(R.id.tv_addy);
        tv_misc1 = findViewById(R.id.tv_misc1);
        tv_misc2 = findViewById(R.id.tv_misc2);
        tv_misc3 = findViewById(R.id.tv_misc3);
        tv_MISC1 = findViewById(R.id.tv_MISC1);
        tv_MISC2 = findViewById(R.id.tv_MISC2);
        tv_MISC3 = findViewById(R.id.tv_MISC3);
        tv_delete = findViewById(R.id.tv_delete);

        //listen for incoming data
        final Bundle incomingIntent = getIntent().getExtras();

        if (incomingIntent != null) {
            int position = incomingIntent.getInt("pos");
            String name = incomingIntent.getString("name");
            int phone = incomingIntent.getInt("phone");
            String email = incomingIntent.getString("email");
            String address = incomingIntent.getString("addy");
            String type = incomingIntent.getString("type");
            int id = incomingIntent.getInt("id");

            //personal contact info
            String nn = incomingIntent.getString("nn");
            String dob = incomingIntent.getString("dob");
            String rel = incomingIntent.getString("rel");

            //business contact info
            String bh = incomingIntent.getString("bh");
            String web = incomingIntent.getString("web");
            String des = incomingIntent.getString("des");

            //fill in the form
            tv_name.setText(name);
            tv_phone.setText(Integer.toString(phone));
            tv_email.setText(email);
            tv_addy.setText(address);

            if (type.matches("Personal")) {
                tv_misc1.setText(nn);
                tv_misc2.setText(dob);
                tv_misc3.setText(rel);
                tv_MISC1.setText("NickName: ");
                tv_MISC2.setText("Date of Birth: ");
                tv_MISC3.setText("Relation: ");
            }
            else if (type.matches("Business")) {
                tv_misc1.setText(bh);
                tv_misc2.setText(web);
                tv_misc3.setText(des);
                tv_MISC1.setText("Business Hours: ");
                tv_MISC2.setText("Website: ");
                tv_MISC3.setText("Description: ");
            }

        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AllContacts.class);

                //listen for incoming data
                final Bundle incomingIntent = getIntent().getExtras();

                i.putExtra("pos", incomingIntent.getInt("pos"));
                i.putExtra("id", incomingIntent.getInt("id"));
                i.putExtra("type", incomingIntent.getString("type"));

                //get strings from et_ view objects
                String newName = tv_name.getText().toString();
                String newPhone = tv_phone.getText().toString();
                String newEmail = tv_email.getText().toString();
                String newAddy = tv_addy.getText().toString();
                String m1 = tv_misc1.getText().toString();
                String m2 = tv_misc2.getText().toString();
                String m3 = tv_misc3.getText().toString();

                //get intents from ContactProfile page
                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("email", newEmail);
                i.putExtra("addy", newAddy);
                i.putExtra("nn", m1);
                i.putExtra("dob", m2);
                i.putExtra("rel", m3);
                i.putExtra("bh", m1);
                i.putExtra("web", m2);
                i.putExtra("des", m3);

                startActivity(i);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //listen for incoming data
                final Bundle incomingIntent = getIntent().getExtras();

                if (incomingIntent.getString("type").matches("Personal")) {
                    PersonalContact p = new PersonalContact(incomingIntent.getInt("id"), incomingIntent.getString("type"), incomingIntent.getString("name"), incomingIntent.getInt("phone"), incomingIntent.getString("email"), incomingIntent.getString("addy"), 0, incomingIntent.getString("dob"), incomingIntent.getString("rel"), incomingIntent.getString("nn"));
                    edit(p, incomingIntent.getInt("pos"));
                } else if (incomingIntent.getString("type").matches("Business")) {
                    BusinessContact b = new BusinessContact(incomingIntent.getInt("id"), incomingIntent.getString("type"), incomingIntent.getString("name"), incomingIntent.getInt("phone"), incomingIntent.getString("email"), incomingIntent.getString("addy"), 0, incomingIntent.getString("bh"), incomingIntent.getString("web"), incomingIntent.getString("des"));
                    edit(b, incomingIntent.getInt("pos"));
                }
            }
        });

        //reference: https://www.youtube.com/watch?v=7vWoi8j5vL4
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactProfile.this);

                builder.setCancelable(true);
                builder.setTitle("Delete Contact");
                builder.setMessage("Do you want to delete this contact?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //listen for incoming data
                        final Bundle incomingIntent = getIntent().getExtras();

                        listOfContacts.getAddressBook().remove(incomingIntent.getInt("pos"));
                        listOfContacts.saveContacts(getApplicationContext());
                        Intent delete = new Intent(getApplicationContext(), AllContacts.class);
                        startActivity(delete);

                    }
                });
                builder.show();
            }
        });
    }

    public void edit(BaseContact b, int position) {
        //check the type of contact
        if (b.getType().matches("Personal")) {

            //set to a personal contact
            PersonalContact pc = (PersonalContact) b;

            Intent i = new Intent(getApplicationContext(), EditPersonalContact.class);

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

            startActivity(i);


        } else if (b.getType().matches("Business")) {

            //set to a business contact
            BusinessContact bc = (BusinessContact) b;

            Intent i = new Intent(getApplicationContext(), EditBusinessContact.class);

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

            startActivity(i);

        }

    }


}
