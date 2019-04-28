package com.example.comtracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPersonalContact extends AppCompatActivity {

    Button btn_done, btn_back6;

    EditText et_name, et_phone, et_email,et_address, et_nn, et_dob, et_rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_contact);

        btn_done = findViewById(R.id.btn_done);
        btn_back6 = findViewById(R.id.btn_back6);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_nn = findViewById(R.id.et_nn);
        et_dob = findViewById(R.id.et_dob);
        et_rel = findViewById(R.id.et_rel);


        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get strings from et_ view objects
                String newName = et_name.getText().toString();
                int newPhone = Integer.parseInt(et_phone.getText().toString());
                String newEmail = et_email.getText().toString();
                String newAddy = et_address.getText().toString();
                String newNN = et_nn.getText().toString();
                String newDOB = et_dob.getText().toString();
                String newRel = et_rel.getText().toString();
                String type = "Personal";
                String pos = null;

                Intent i = new Intent(v.getContext(), AllContacts.class);

                //get intents from AddNewContact page
                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("email", newEmail);
                i.putExtra("addy", newAddy);
                i.putExtra("nn", newNN);
                i.putExtra("dob", newDOB);
                i.putExtra("rel", newRel);
                i.putExtra("type", type);
                i.putExtra("pos", pos);


                startActivity(i);
            }
        });

        btn_back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AllContacts.class);
                startActivity(i);
            }
        });
    }
}
