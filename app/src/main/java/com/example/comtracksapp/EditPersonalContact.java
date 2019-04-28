package com.example.comtracksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditPersonalContact extends AppCompatActivity {
    EditText et_name, et_phone, et_email, et_addy, et_nn, et_dob, et_rel;

    Button btn_back, btn_done;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_contact);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_addy = findViewById(R.id.et_address);
        et_nn = findViewById(R.id.et_nn);
        et_dob = findViewById(R.id.et_dob);
        et_rel = findViewById(R.id.et_rel);
        btn_done = findViewById(R.id.btn_done_edit_per);
        btn_back = findViewById(R.id.btn_back);

        //listen for incoming data
        Bundle incomingIntent = getIntent().getExtras();

        if (incomingIntent != null) {
            positionToEdit = incomingIntent.getInt("pos");
            String name = incomingIntent.getString("name");
            int phone = incomingIntent.getInt("phone");
            String email = incomingIntent.getString("email");
            String address = incomingIntent.getString("addy");
            String nn = incomingIntent.getString("nn");
            String dob = incomingIntent.getString("dob");
            String rel = incomingIntent.getString("rel");

            //fill in the form
            et_name.setText(name);
            et_phone.setText(Integer.toString(phone));
            et_email.setText(email);
            et_addy.setText(address);
            et_nn.setText(nn);
            et_dob.setText(dob);
            et_rel.setText(rel);

        }

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get strings from et_ view objects
                String newName = et_name.getText().toString();
                int newPhone = Integer.parseInt(et_phone.getText().toString());
                String newEmail = et_email.getText().toString();
                String newAddy = et_addy.getText().toString();
                String newNN = et_nn.getText().toString();
                String newDOB = et_dob.getText().toString();
                String newRel = et_rel.getText().toString();
                String type = "Personal";

                //put the strings into a message
                Intent i = new Intent(v.getContext(), ContactProfile.class);

                i.putExtra("pos", positionToEdit);
                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("email", newEmail);
                i.putExtra("addy", newAddy);
                i.putExtra("type", type);
                i.putExtra("nn", newNN);
                i.putExtra("dob", newDOB);
                i.putExtra("rel", newRel);


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
                i.putExtra("type", "Personal");
                i.putExtra("dob", incomingIntent.getString("dob"));
                i.putExtra("rel", incomingIntent.getString("rel"));
                i.putExtra("nn", incomingIntent.getString("nn"));

                startActivity(i);
            }
        });
    }
}
