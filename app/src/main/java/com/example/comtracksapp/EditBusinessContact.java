package com.example.comtracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBusinessContact extends AppCompatActivity {

    EditText et_name, et_phone, et_email, et_addy, et_bh, et_web, et_des;

    Button btn_back, btn_done;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_business_contact);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_addy = findViewById(R.id.et_address);
        et_bh = findViewById(R.id.et_bh);
        et_web = findViewById(R.id.et_web);
        et_des = findViewById(R.id.et_des);
        btn_done = findViewById(R.id.btn_done_edit_bus);
        btn_back = findViewById(R.id.btn_back_edit_bus);

        //listen for incoming data
        Bundle incomingIntent = getIntent().getExtras();

        if (incomingIntent != null) {
            positionToEdit = incomingIntent.getInt("pos");
            String name = incomingIntent.getString("name");
            int phone = incomingIntent.getInt("phone");
            String email = incomingIntent.getString("email");
            String address = incomingIntent.getString("addy");
            String bh = incomingIntent.getString("bh");
            String web = incomingIntent.getString("web");
            String des = incomingIntent.getString("des");

            //fill in the form
            et_name.setText(name);
            et_phone.setText(Integer.toString(phone));
            et_email.setText(email);
            et_addy.setText(address);
            et_bh.setText(bh);
            et_web.setText(web);
            et_des.setText(des);
        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get strings from et_ view objects
                String newName = et_name.getText().toString();
                int newPhone = Integer.parseInt(et_phone.getText().toString());
                String newEmail = et_email.getText().toString();
                String newAddy = et_addy.getText().toString();
                String newBH = et_bh.getText().toString();
                String newWeb = et_web.getText().toString();
                String newDes = et_des.getText().toString();
                String type = "Business";

                //put the strings into a message for MainActivity

                Intent i = new Intent(v.getContext(), ContactProfile.class);

                i.putExtra("pos", positionToEdit);
                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("email", newEmail);
                i.putExtra("addy", newAddy);
                i.putExtra("type", type);
                i.putExtra("bh", newBH);
                i.putExtra("web", newWeb);
                i.putExtra("des", newDes);


                startActivity(i);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ContactProfile.class);

                //listen for incoming data
                Bundle incomingIntent = getIntent().getExtras();

                i.putExtra("pos", positionToEdit);
                i.putExtra("name", incomingIntent.getString("name"));
                i.putExtra("phone", incomingIntent.getInt("phone"));
                i.putExtra("email", incomingIntent.getString("email"));
                i.putExtra("addy", incomingIntent.getString("addy"));
                i.putExtra("type", "Business");
                i.putExtra("bh", incomingIntent.getString("bh"));
                i.putExtra("web", incomingIntent.getString("web"));
                i.putExtra("des", incomingIntent.getString("des"));

                startActivity(i);
            }
        });
    }
}
