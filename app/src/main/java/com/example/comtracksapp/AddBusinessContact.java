package com.example.comtracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBusinessContact extends AppCompatActivity {

    Button btn_done2, btn_back7;

    EditText et_name, et_phone, et_email, et_address, et_bh, et_web, et_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business_contact);

        btn_back7 = findViewById(R.id.btn_back7);
        btn_done2 = findViewById(R.id.btn_done2);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_bh = findViewById(R.id.et_bh);
        et_web = findViewById(R.id.et_web);
        et_des = findViewById(R.id.et_des);

        btn_done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get strings from et_ view objects
                String newName = et_name.getText().toString();
                int newPhone = Integer.parseInt(et_phone.getText().toString());
                String newEmail = et_email.getText().toString();
                String newAddy = et_address.getText().toString();
                String newBH = et_bh.getText().toString();
                String newWeb = et_web.getText().toString();
                String newDes = et_des.getText().toString();
                String type = "Business";
                String pos = null;

                Intent i = new Intent(v.getContext(), AllContacts.class);

                //get intents from AddNewContact page
                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("email", newEmail);
                i.putExtra("addy", newAddy);
                i.putExtra("bh", newBH);
                i.putExtra("web", newWeb);
                i.putExtra("des", newDes);
                i.putExtra("type", type);
                i.putExtra("pos", pos);

                startActivity(i);

            }
        });

        btn_back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AllContacts.class);
                startActivity(i);
            }
        });
    }
}
