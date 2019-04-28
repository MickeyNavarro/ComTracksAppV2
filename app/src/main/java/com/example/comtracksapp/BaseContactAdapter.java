package com.example.comtracksapp;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseContactAdapter extends BaseAdapter {

    Activity mActivity;
    AddressBook addressBook;


    public BaseContactAdapter(Activity mActivity, AddressBook addressBook) {
        this.mActivity = mActivity;
        this.addressBook = addressBook;
    }

    @Override
    public int getCount() {
        return addressBook.getAddressBook().size();
    }

    @Override
    public Object getItem(int position) {

        return addressBook.getAddressBook().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View onePersonLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.contact_line,parent, false );

        TextView tv_name_line = onePersonLine.findViewById(R.id.tv_name_line);
        TextView tv_type_line = onePersonLine.findViewById(R.id.tv_type_line);

        BaseContact b = (BaseContact) this.getItem(position);

        tv_name_line.setText(b.getName());
        tv_type_line.setText(b.getType());

        return onePersonLine;
    }

}
